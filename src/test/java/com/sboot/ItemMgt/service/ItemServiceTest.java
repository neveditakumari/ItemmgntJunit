package com.sboot.ItemMgt.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sboot.ItemMgt.dao.ItemDao;
import com.sboot.ItemMgt.model.Item;
@SpringBootTest
class ItemServiceTest {
	@Autowired
	ItemService isvc;
	
	
	@MockBean
	ItemDao idao;
	
	@Test
	void testAddItem() {
		Item i = new Item(101,"Geometry Box",75.0f);
		Mockito.when(idao.save(i)).thenReturn(i);
		assertEquals(i,isvc.addItem(i));
	}

	@Test
	void testGetItem() {
		Optional<Item> item = Optional.of(new Item(101,"Scale",10.0f));
		
		Mockito.when(idao.findById(101)).thenReturn(item);
		
		assertEquals("Scale",isvc.getItem(101).getItemName());
	}

	@Test
	void testGetAll() {
		List<Item> lItem = Stream.of(new Item(101,"Pen",50.0f),new Item(102,"Pencil",10.0f)).collect(Collectors.toList());
		Mockito.when(idao.findAll()).thenReturn(lItem);
		
		assertEquals(2,isvc.getAll().size());
	}

	@Test
	void testUpdateItem() {
		Item i = new Item(101,"Geometry Box",75.0f);
		
		
		//Mockito.when(idao.findById(101)).thenReturn(item);
		Mockito.when(idao.save(i)).thenReturn(i);
		assertEquals(i,isvc.updateItem(i));
	}

	@Test
	void testDeleteItem() {
		isvc.deleteItem(101);
		Mockito.verify(idao).deleteById(101);
	}

}
