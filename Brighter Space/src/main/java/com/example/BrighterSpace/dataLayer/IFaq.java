package com.example.BrighterSpace.dataLayer;

import com.example.BrighterSpace.model.Faq;

import java.sql.SQLException;
import java.util.List;

public interface IFaq {
    List<Faq> getAllFaqsByCourseId(int courseId) throws SQLException;
    String createFaq(Faq faq, int courseId) throws SQLException;
    Faq getFaqById(int faqId) throws SQLException;
    Faq updateFaqById(Faq faq) throws SQLException;
    String deleteFaqById(int faqId) throws SQLException;
}
