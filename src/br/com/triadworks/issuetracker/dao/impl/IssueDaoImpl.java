package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Issue;

@Repository("issueDao")
@Transactional
public class IssueDaoImpl implements IssueDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Issue> listaTudo() {
		return entityManager
				.createQuery("from Issue")
				.getResultList();
	}

	@Override
	public void salva(Issue issue) {
		entityManager.persist(issue);
	}

	@Override
	public void atualiza(Issue issue) {
		entityManager.merge(issue);
	}

	@Override
	public void remove(Issue issue) {
		entityManager.remove(entityManager.merge(issue));
	}

}
