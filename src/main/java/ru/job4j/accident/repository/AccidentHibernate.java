package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    protected <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident save(Accident accident, String[] rIds) {
        accident = defineRules(accident, rIds);
        if (accident.getId() == 0) {
            System.out.println("create");
            accident = create(accident);
        } else {
            System.out.println("update");
            update(accident);
        }
        return accident;

    }

    public Accident create(Accident accident) {
        return (Accident) this.tx(session -> {
            session.save(accident);
            return session.createQuery("from Accident order by id desc").setMaxResults(1).getSingleResult();
        });
    }

    public Accident update(Accident accident) {
        return this.tx(session -> {
            session.update(accident);
            return accident;
        });
    }

    private Accident defineRules(Accident accident, String[] rIds) {
        for (String rId : rIds) {
            accident.addRule(findRuleById(Integer.parseInt(rId)));
        }
        return accident;
    }

    public List<Accident> getAllAccidents() {
        return this.tx(session ->
                session.createQuery(
                        "select distinct a "
                        + "from Accident a "
                        + "join fetch a.type "
                        + "left join fetch a.rules "
                        + "order by a.id asc"
                ).list()
        );
    }

    public Accident findAccidentById(int id) {
        return (Accident) this.tx(session ->
                session.createQuery(
                        "select distinct a "
                        + "from Accident a "
                        + "join fetch a.type "
                        + "left join fetch a.rules "
                        + "where a.id = :id"
                ).setParameter("id", id)
                .uniqueResult()
        );
    }

    public boolean deleteAccidentById(int id) {
        return this.tx(session -> {
            session.delete(findAccidentById(id));
            return true;
        });
    }

    public Rule findRuleById(int id) {
        return this.tx(session -> session.get(Rule.class, id));
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return this.tx(session -> session.createQuery("from AccidentType order by id asc").list());
    }

    public Collection<Rule> getAllRules() {
        return this.tx(session -> session.createQuery("from Rule order by id asc").list());
    }
}