package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	
	@Autowired
    private MockMvc mockMvc;

    
	@Test
	public void testPostData() throws Exception {
		String st = "{\"bookId\":1,\"title\": \"All is well\",\"author\": \"ABC\",\"availableCopies\": 34,\"totalCopies\":100}";
		mockMvc.perform(MockMvcRequestBuilders.post("/book")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())      
				.andReturn();
	}
	
	@Test
	public void testUpdateData() throws Exception {
		String st = "{\"bookId\":1,\"title\": \"All\",\"author\": \"ABC\",\"availableCopies\": 34,\"totalCopies\":100}";
		mockMvc.perform(MockMvcRequestBuilders.put("/book/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	

    @Test
    public void testGetByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$").isArray())
                .andReturn();
    }


    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}
	

	 @Test
   public void testControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.BookController");
   }

   @Test
   public void testRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.BookRepo");
   }

   @Test
   public void testServiceClassExists() {
       checkClassExists("com.examly.springapp.service.BookService");
   }

   @Test
   public void testModelClassExists() {
       checkClassExists("com.examly.springapp.model.Book");
   }

   @Test
   public void testModelHasBookTitleField() {
       checkFieldExists("com.examly.springapp.model.Book", "title");
   }

   @Test
   public void testModelHasAuthorForField() {
       checkFieldExists("com.examly.springapp.model.Book", "author");
   }

   @Test
   public void testModelHasTotalNoOfCopiesForField() {
       checkFieldExists("com.examly.springapp.model.Book", "totalCopies");
   }
   
   @Test
   public void testModelHasAvailableCopiesForField() {
       checkFieldExists("com.examly.springapp.model.Book", "availableCopies");
   }


   @Test
   public void testModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.Book", "javax.persistence.Entity");
   }

   @Test
   public void testRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.BookRepo", "org.springframework.stereotype.Repository");
   }
   
   @Test
   public void testServiceHasServiceAnnotation() {
       checkAnnotationExists("com.examly.springapp.service.BookService", "org.springframework.stereotype.Service");
   }
   
   @Test
   public void testControllerHasRestControllerAnnotation() {
       checkAnnotationExists("com.examly.springapp.controller.BookController", "org.springframework.web.bind.annotation.RestController");
   }
   
   @Test 
   public void testControllerFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/controller"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testModelFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testRepositoryFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/repository"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testServiceFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/service"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

}
