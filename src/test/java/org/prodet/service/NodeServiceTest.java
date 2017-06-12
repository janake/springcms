package org.prodet.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.prodet.repository.domain.Node;
import org.prodet.repository.repository.NodeRepositoryInterface;
import org.prodet.service.DAO.NodeDao;

@RunWith(MockitoJUnitRunner.class)
public class NodeServiceTest {

	@Mock
	NodeRepositoryInterface nodeRepository;
	
	@Mock
	NodeDao nodeFromController;
	
	@Test
	public void testSave() {
		
		Node node = mock(Node.class);
//		NodeDao nodeFromController = mock(NodeDao.class);
		when(nodeFromController.getTitle()).thenReturn("Ez az első címem");
		when(nodeFromController.getBody()).thenReturn("Ez az első body-m");
		
		node.setTitle(nodeFromController.getTitle());
		node.setBody(nodeFromController.getBody());
		Node expected = nodeRepository.save(node);
		
		Node actual = nodeRepository.findOne(expected.getId());
		
		Assert.assertEquals(expected.getTitle(), actual.getTitle());		
	}

}
