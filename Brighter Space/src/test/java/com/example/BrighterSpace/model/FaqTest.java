package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IFaq;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class FaqTest {

    @Test
    public void faqTestFromAllArgumentsTest() {
        Faq f1 = new Faq(1, "When is the exam?", "Its on this Saturday", 1);
        Assertions.assertEquals(f1.getFaqId(), 1);
        Assertions.assertEquals(f1.getFaqQuestion(), "When is the exam?");
        Assertions.assertEquals(f1.getFaqAnswer(), "Its on this Saturday");
        Assertions.assertEquals(f1.getCourseId(), 1);
    }

    @Test
    public void createFaqTest() throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFaq faqOperations = new Faq(factory.getFaqMockFactory().create());
        //IFaq faqOperations = new Faq(factory.getFaqMockFactory().create());
        Faq f1 = new Faq(1, "When is the exam?", "Its on this Saturday");
        String output = faqOperations.createFaq(f1, 1);
        Assertions.assertEquals(output, "FAQ Created!");
    }

    @Test
    public void getFaqByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFaq faqOperations = new Faq(factory.getFaqMockFactory().create());
        Faq f1 = faqOperations.getFaqById(1);
        Assertions.assertEquals(f1.getFaqId(), 1);
        Assertions.assertEquals(f1.getFaqQuestion(), "When is the exam?");
        Assertions.assertEquals(f1.getFaqAnswer(), "Its on this Saturday");
        Assertions.assertEquals(f1.getCourseId(), 1);
    }

    @Test
    public void updateFaqByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFaq faqOperations = new Faq(factory.getFaqMockFactory().create());
        Faq f = new Faq(1, "When is the exam?", "Its on this Saturday", 1);
        Faq f1 = faqOperations.updateFaqById(f);
        Assertions.assertEquals(f1.getFaqId(), 1);
        Assertions.assertEquals(f1.getFaqQuestion(), "When is the exam?");
        Assertions.assertEquals(f1.getFaqAnswer(), "Its on this Saturday");
        Assertions.assertEquals(f1.getCourseId(), 1);
    }

    @Test
    public void deleteFaqByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFaq faqOperations = new Faq(factory.getFaqMockFactory().create());
        String output = faqOperations.deleteFaqById(1);
        Assertions.assertEquals(output, "FAQ Deleted!");
    }
}
