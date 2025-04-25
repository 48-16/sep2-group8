package ui.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import networking.shop.shopClient;
import utils.ErrorPopUp;
import model.product;
import model.price;

import java.util.HashMap;
import java.util.Map;
import networking.apointment.appointmentClient;

public class mainAdminViewControler {
    @FXML
    private Button firstHour;
    @FXML
    private Button SecondHour;
    @FXML
    private Button ThirdHour;
    @FXML
    private Button FourthHour;
    @FXML
    private Button FifthHour;
    @FXML
    private Button SixthHour;
    @FXML
    private Button SeventhHour;
    @FXML
    private Button EightHour;
    @FXML
    private Button DeleteDay;
    @FXML
    private Button DeleteTime;
    @FXML
    private Button CancelApointment;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<product> productTable;
    @FXML
    private TableColumn<product, String> nameColumn;
    @FXML
    private TableColumn<product, Integer> quantityColumn;
    @FXML
    private Button addNewProductButton;
    @FXML
    private TextField newProductNameField;
    @FXML
    private TextField newProductQuantityField;

    private final mainViewAdminModel viewModel;
    private final ShopModel shopModel;
    private Map<String, Button> timeButtons = new HashMap<>();
    private String selectedTimeSlot = null;
    private ErrorPopUp errorPopUp = new ErrorPopUp();

    private static final String AVAILABLE_STYLE = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
    private static final String BOOKED_STYLE = "-fx-background-color: #FFA500; -fx-text-fill: white;";
    private static final String SELECTED_STYLE = "-fx-background-color: #2196F3; -fx-text-fill: white;";

    public mainAdminViewControler(mainViewAdminModel viewModel, shopClient shopService) {
        this.viewModel = viewModel;
        this.shopModel = new ShopModel(shopService);
    }

    public void initialize() {
        timeButtons.put("8:00-9:00", firstHour);
        timeButtons.put("9:00-10:00", SecondHour);
        timeButtons.put("10:00-11:00", ThirdHour);
        timeButtons.put("11:00-12:00", FourthHour);
        timeButtons.put("12:00-13:00", FifthHour);
        timeButtons.put("13:00-14:00", SixthHour);
        timeButtons.put("14:00-15:00", SeventhHour);
        timeButtons.put("15:00-16:00", EightHour);

        datePicker.valueProperty().bindBidirectional(viewModel.getSelectedDateProperty());

        datePicker.valueProperty().addListener((Observable, oldDate, newDate) -> {
            if (newDate != null) {
                updateTimeSlotButtons();
                clearSelectedTimeSlot();
            }
        });

        DeleteTime.setDisable(true);
        CancelApointment.setDisable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTable.setItems(shopModel.getProducts());
    }

    private void updateTimeSlotButtons() {
        for (Map.Entry<String, Button> entry : timeButtons.entrySet()) {
            String timeSlot = entry.getKey();
            Button button = entry.getValue();

            boolean isBooked = !viewModel.isTimeSlotAvailable(timeSlot);

            if (isBooked) {
                button.setStyle(BOOKED_STYLE);
            } else {
                button.setStyle(AVAILABLE_STYLE);
            }

            button.setDisable(false);
        }
    }

    private void clearSelectedTimeSlot() {
        selectedTimeSlot = null;
        viewModel.setSelectedTimeSlot(null);
        DeleteTime.setDisable(true);
        CancelApointment.setDisable(true);

        updateTimeSlotButtons();
    }

    private void selectTimeSlot(String timeSlot, Button button) {
        if (selectedTimeSlot != null) {
            Button prevButton = timeButtons.get(selectedTimeSlot);
            if (!viewModel.isTimeSlotAvailable(selectedTimeSlot)) {
                prevButton.setStyle(BOOKED_STYLE);
            } else {
                prevButton.setStyle(AVAILABLE_STYLE);
            }
        }

        selectedTimeSlot = timeSlot;
        viewModel.setSelectedTimeSlot(timeSlot);
        button.setStyle(SELECTED_STYLE);

        boolean isBooked = !viewModel.isTimeSlotAvailable(timeSlot);
        DeleteTime.setDisable(!isBooked);
        CancelApointment.setDisable(!isBooked);
    }

    @FXML
    public void choosefirstHour() {
        selectTimeSlot("8:00-9:00", firstHour);
    }

    @FXML
    public void chooseSecondHour() {
        selectTimeSlot("9:00-10:00", SecondHour);
    }

    @FXML
    public void chooseThirdHour() {
        selectTimeSlot("10:00-11:00", ThirdHour);
    }

    @FXML
    public void chooseFourthHour() {
        selectTimeSlot("11:00-12:00", FourthHour);
    }

    @FXML
    public void chooseFifthHour() {
        selectTimeSlot("12:00-13:00", FifthHour);
    }

    @FXML
    public void chooseSixthHour() {
        selectTimeSlot("13:00-14:00", SixthHour);
    }

    @FXML
    public void chooseSeventhHour() {
        selectTimeSlot("14:00-15:00", SeventhHour);
    }

    @FXML
    public void chooseEightHour() {
        selectTimeSlot("15:00-16:00", EightHour);
    }

    @FXML
    public void DeleteDay() {
        if (datePicker.getValue() == null) {
            errorPopUp.show("Error", "Please select a date");
            return;
        }

        boolean success = viewModel.deleteDay();
        if (success) {
            updateTimeSlotButtons();
            clearSelectedTimeSlot();
        }
    }

    @FXML
    public void DeleteTime() {
        if (selectedTimeSlot == null) {
            errorPopUp.show("Error", "Please select a time slot");
            return;
        }

        boolean success = viewModel.deleteTimeSlot();
        if (success) {
            updateTimeSlotButtons();
            clearSelectedTimeSlot();
        }
    }

    @FXML
    public void DeleteApointment() {
        if (selectedTimeSlot == null) {
            errorPopUp.show("Error", "Please select a time slot");
            return;
        }

        boolean success = viewModel.cancelAppointment();
        if (success) {
            updateTimeSlotButtons();
            clearSelectedTimeSlot();
        }
    }

    @FXML
    public void addNewProduct() {
        String name = newProductNameField.getText();
        String quantityStr = newProductQuantityField.getText();

        if (name == null || name.isEmpty()) {
            errorPopUp.show("Error", "Please enter a product name");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                errorPopUp.show("Error", "Quantity must be greater than 0");
                return;
            }

            shopModel.addNewProduct(name, quantity);
            productTable.refresh();
            
            newProductNameField.clear();
            newProductQuantityField.clear();
        } catch (NumberFormatException e) {
            errorPopUp.show("Error", "Please enter a valid quantity");
        }
    }
}