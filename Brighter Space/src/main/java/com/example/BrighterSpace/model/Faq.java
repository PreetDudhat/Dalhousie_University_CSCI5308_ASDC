package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IFaq;
import com.example.BrighterSpace.dataLayer.mocks.IFaqDAO;

import java.sql.SQLException;
import java.util.List;

public class Faq implements IFaq {
    private int faqId;
    private String faqQuestion;
    private String faqAnswer;
    private int courseId;
    private IFaqDAO faqDAO;

    public Faq(int faqId, String faqQuestion, String faqAnswer) {
        this.faqId = faqId;
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
    }

    public Faq(int faqId, String faqQuestion, String faqAnswer, int courseId) {
        this.faqId = faqId;
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
        this.courseId = courseId;
    }

    public Faq(IFaqDAO doa) {
        this.faqDAO = doa;
    }

    public Faq() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }

    public String createFaq(Faq faq, int courseId) throws SQLException {
        return faqDAO.createFaq(faq, courseId);
    }

    public Faq getFaqById(int faqId) throws SQLException {
        return faqDAO.getFaqById(faqId);
    }

    public Faq updateFaqById(Faq faq) throws SQLException {
        return faqDAO.updateFaqById(faq);
    }

    public String deleteFaqById(int faqId) throws SQLException {
        return faqDAO.deleteFaqById(faqId);
    }

    public List<Faq> getAllFaqsByCourseId(int courseId) throws SQLException {
        return faqDAO.getAllFaqsByCourseId(courseId);
    }
}
