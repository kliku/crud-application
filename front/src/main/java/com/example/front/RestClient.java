package com.example.front;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestClient {

    static final Logger logger = LogManager.getLogger(RestClient.class.getName());

    public List<Company> getCompanyList(String searchTxt) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/company" + (searchTxt != null ? "?searchTxt=" + searchTxt : "");
            HttpGet getRequest = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(getRequest);
            String json = EntityUtils.toString(httpResponse.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            Company[] companyList = objectMapper.readValue(json, Company[].class);
            return new ArrayList<>(Arrays.asList(companyList));
        } catch (Exception e) {
            logger.error("Error list is empty");
            return new ArrayList<>();
        }
    }

    public void addCompany(CompanyAddForm companyAddForm) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/company";
            HttpPost postRequest = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            postRequest.setEntity(new StringEntity(objectMapper.writeValueAsString(companyAddForm)));
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(postRequest);
        } catch (Exception e) {
            logger.error("Error during add company.", e);
        }
    }

    public void addEmployee(EmployeeAddForm employeeAddForm) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/employee";
            HttpPost postRequest = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            postRequest.setEntity(new StringEntity(objectMapper.writeValueAsString(employeeAddForm)));
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(postRequest);
        } catch (Exception e) {
            logger.error("Error during add employee for company id: " + employeeAddForm.getCompanyId(), e);
        }
    }

    public void deleteCompany(Long id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/company/" + id;
            HttpDelete httpDelete = new HttpDelete(url);
            ObjectMapper objectMapper = new ObjectMapper();
            httpDelete.setHeader("Accept", "application/json");
            httpDelete.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpDelete);
        } catch (Exception e) {
            logger.error("Error during delete company with id: " + id, e);
        }
    }
}
