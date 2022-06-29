package com.example.front;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;


public class CrudController {

    @FXML
    private TableView<Company> companyTable;

    @FXML
    private TableColumn<Company, String> companyNameColumn;
    @FXML
    private TableColumn<Company, String> companyNipColumn;
    @FXML
    private TableColumn<Company, Void> companyDeleteColumn;
    @FXML
    private TableColumn<Employee, String> employeeNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeSurnameColumn;

    @FXML
    private TableView employeeTable;

    @FXML
    private TextField searchField;

    @FXML
    private TextField companyNameTxtField;

    @FXML
    private TextField companyNipTxtField;

    @FXML
    private TextField employeeNameTxtField;

    @FXML
    private TextField employeeSurnameTxtField;


    private final RestClient restClient = new RestClient();

    private Company selectedCompany;

    private List<Company> companyList;

    public void initialize() {
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyNipColumn.setCellValueFactory(new PropertyValueFactory<>("companyNip"));
        companyTable.getItems().setAll(restClient.getCompanyList(null));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeeSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeSurname"));
        companyTable.setRowFactory(tw -> {
            TableRow<Company> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                selectedCompany = row.getItem();
                employeeTable.getItems().clear();
                employeeTable.getItems().setAll(selectedCompany.getEmployeeDtoList());
            });
            return row;
        });
        initDeleteCompany();
    }



    @FXML
    public void onSearch(KeyEvent keyEvent) {
        String searchText = this.searchField.getText();
        companyList = restClient.getCompanyList(searchText);
        companyTable.getItems().setAll(companyList);
        System.out.println(searchText);
    }

    public void addCompany(MouseEvent mouseEvent) {

        CompanyAddForm companyAddForm = new CompanyAddForm(companyNameTxtField.getText(), companyNipTxtField.getText());
        restClient.addCompany(companyAddForm);
        onSearch(null);
    }

    public void addEmployee(MouseEvent mouseEvent) {
        EmployeeAddForm employeeAddForm = new EmployeeAddForm(employeeNameTxtField.getText()
                , employeeSurnameTxtField.getText(), selectedCompany.getId());
        restClient.addEmployee(employeeAddForm);
        onSearch(null);
        employeeTable.getItems().setAll(companyList.stream().filter(company -> company.getId()
                        .equals(selectedCompany.getId()))
                .findFirst().map(Company::getEmployeeDtoList).orElse(new ArrayList<>()));
    }


    private void initDeleteCompany() {
        Callback<TableColumn<Company, Void>, TableCell<Company, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Company, Void> call(final TableColumn<Company, Void> param) {
                final TableCell<Company, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Usuń");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Company data = getTableView().getItems().get(getIndex());
                            deleteCompany(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        this.companyDeleteColumn.setCellFactory(cellFactory);
    }

    private void deleteCompany(Company company) {
        restClient.deleteCompany(company.getId());
        onSearch(null);
    }
}
