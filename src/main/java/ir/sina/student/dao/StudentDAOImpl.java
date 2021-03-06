package ir.sina.student.dao;

import ir.sina.student.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Student> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<Student> query = session.createQuery("from Student ", Student.class);

        List<Student> students = query.getResultList();

        return students;
    }

    @Override
    public Student getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Student student = session.get(Student.class, id);

        return student;
    }

    @Override
    public void save(Student student) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(student);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Student where id=:studentID");

        query.setParameter("studentID", id);

        query.executeUpdate();
    }
}
