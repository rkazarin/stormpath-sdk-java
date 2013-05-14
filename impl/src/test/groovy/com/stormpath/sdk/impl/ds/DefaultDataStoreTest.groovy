package com.stormpath.sdk.impl.ds;

import org.testng.annotations.Test


import static org.easymock.EasyMock.*
import static org.testng.Assert.*

import com.stormpath.sdk.impl.error.DefaultError;
import com.stormpath.sdk.impl.http.*;
import com.stormpath.sdk.impl.http.support.DefaultRequest;
import com.stormpath.sdk.impl.http.support.Version;
import com.stormpath.sdk.impl.resource.AbstractResource;
import com.stormpath.sdk.impl.util.Assert;
import com.stormpath.sdk.impl.util.StringInputStream;
import com.stormpath.sdk.impl.util.StringUtils;
import com.stormpath.sdk.resource.Resource;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.resource.Saveable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;


class DefaultDataStoreTest {
	
	@Test
	 void test_Save_ResourceIsNull() {
	
		RequestExecutor rE = createStrictMock(RequestExecutor)
		DefaultDataStore ds = new DefaultDataStore(rE)
		AbstractResource  sar

		try 
		{	
			ds.save(sar)
		 } 
		catch(IllegalArgumentException e) 
		{
			assertEquals "java.lang.IllegalArgumentException: resource argument cannot be null.", e.toString()

		 }	

	}

	// testing helper function qualify(String href)
  	@Test
	void test_Qualify_1() {

		RequestExecutor rE = createStrictMock(RequestExecutor)
		DefaultDataStore ds = new DefaultDataStore(rE)

		String href = "save"
		
    		StringBuilder expected = new StringBuilder(ds.baseUrl)
		expected.append("/")
		expected.append(href) //expected = "https://api.stormpath.com/v1/save"
		
		String result = ds.qualify(href)
		assertEquals result.toString(), expected.toString()
	}

 	// testing helper function qualify(String href)
   	@Test
	void test_Qualify_2() {

		RequestExecutor rE = createStrictMock(RequestExecutor)
		DefaultDataStore ds = new DefaultDataStore(rE)

		String href = "/save"
    
		StringBuilder expected = new StringBuilder(ds.baseUrl)
		expected.append(href) //expected = "https://api.stormpath.com/v1/save"

		String result = ds.qualify(href)
		assertEquals result.toString(), expected.toString()

	}


	// testing helper function qualify(String href)
  	@Test
  	void test_Qualify_3() {

		RequestExecutor rE = createStrictMock(RequestExecutor)
		DefaultDataStore ds = new DefaultDataStore(rE)

		String href = "save"
		String expected = "https://api.stormpath.com/v1/save"
    		String result = ds.qualify(href)
    		assertEquals result.toString(), expected

																														  }


  	@Test
	void test_Save_ResourceNotInstanceOfAbstractResource() {

		RequestExecutor rE = createStrictMock(RequestExecutor)
		DefaultDataStore ds = new DefaultDataStore(rE)

		// mocking resource
		Resource ar = createStrictMock(Resource)
		String expString = "must be an instance of class com.stormpath.sdk.impl.resource.AbstractResource"
   		
		try {
		  ds.save(ar)
		 } 

		catch(IllegalArgumentException e) {
      		
			String eString = e.toString();
			int i = eString.indexOf(expString)
			String  resString = eString.substring(i)
			assertEquals expString, resString
		}
	}			


	@Test
  	void test_Save_ResourceIsNotInstanceOfSaveable() {

		RequestExecutor rE = createStrictMock(RequestExecutor)
  		DefaultDataStore ds = new DefaultDataStore(rE)

  		// mocking resource
  		AbstractResource ar = createStrictMock(AbstractResource)
		// Expected error (partial)
   		String expString = "must be an instance of interface com.stormpath.sdk.resource.Saveable"

    		try {
		  ds.save(ar)
		} 
		catch(IllegalArgumentException e) {
			String eString = e.toString();
			int i = eString.indexOf(expString)
			String  resString = eString.substring(i)
	       	 	assertEquals expString, resString
		}

	}
	
	// testing helper function NeedsToBeFullyQualified (String href)
  	@Test
 	void test_Save_NeedsToBeFullyQualified_True(){

		RequestExecutor rE = createStrictMock(RequestExecutor)
  		DefaultDataStore ds = new DefaultDataStore(rE)

  		String href = "ftp://stormpath.tom"
  		assertEquals ds.needsToBeFullyQualified(href), true
  }

  
	// testing helper function NeedsToBeFullyQualified (String href) 
	@Test
	void test_NeedsToBeFullyQualified_False(){

		RequestExecutor rE = createStrictMock(RequestExecutor)
		DefaultDataStore ds = new DefaultDataStore(rE)

		String href = "http://stormrpath.com"
		assertEquals ds.needsToBeFullyQualified(href), false

	}

  	@Test
	void test_DEFAULT_SERVER_HOST() {
		assertEquals DefaultDataStore.DEFAULT_SERVER_HOST, "api.stormpath.com"

	}


	@Test
	void test_DEFAULT_API_VERSION() {

		assertEquals DefaultDataStore.DEFAULT_API_VERSION, 1

	}

	@Test
	void test_DefaultDataStore_Constructor_RequestExecutorNotNull() {
		String baseUrl = "https://api.stormpath.com/v1"
		RequestExecutor rE
		try {
			DefaultDataStore dds = new DefaultDataStore(rE, baseUrl)
		} 
		catch (IllegalArgumentException e) {
      			assertEquals "java.lang.IllegalArgumentException: RequestExecutor cannot be null.", e.toString()

		}
		
	}


  @Test
  void test_DefaultDataStore_Constructor_BaseUrlNotNull() {
		String baseUrl 
		RequestExecutor rE
		try {
			DefaultDataStore dds = new DefaultDataStore(rE, baseUrl)
		} catch (IllegalArgumentException e) {
			assertEquals "java.lang.IllegalArgumentException: baseUrl cannot be null", e.toString()
		}

	}


}



