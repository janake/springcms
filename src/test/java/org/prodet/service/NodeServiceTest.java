package org.prodet.service;

<<<<<<< b53bac087c1d730a00ff513c525d4d431328523e
<<<<<<< 5e639b71e57ad4bdfa3aa33eaf2ef4f2f998ecb0
=======
<<<<<<< 436172b0d05d042895cad0d0c8a1f8095c771b25
=======
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
>>>>>>> test changes
>>>>>>> test changes
public class NodeServiceTest {
	
=======
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

>>>>>>> vmi javitas
}
