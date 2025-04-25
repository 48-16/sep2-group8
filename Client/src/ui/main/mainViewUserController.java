package ui.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import networking.shop.shopClient;
import utils.ErrorPopUp;
import model.product;

import java.util.HashMap;
import java.util.Map;

public class mainViewUserController
{
  @FXML private Button addToCartButton;
  @FXML private TableColumn<product, String> nameColumn;
  @FXML private TableColumn<product, Integer> quantityColumn;
  @FXML private TableView<product> productTable;
  @FXML private Button submitPurchaseButton;
  @FXML private Button firstHour;
  @FXML private Button SecondHour;
  @FXML private Button ThirdHour;
  @FXML private Button FourthHour;
  @FXML private Button FifthHour;
  @FXML private Button SixthHour;
  @FXML private Button SeventhHour;
  @FXML private Button EightHour;
  @FXML private Button ConfirmBooking;
  @FXML private DatePicker datePicker;

  private final mainViewUserModel viewModel;
  private final ShopModel shopModel;
  private Map<String, Button> timeButtons = new HashMap<>();
  private String selectedTimeSlot = null;
  private ErrorPopUp errorPopUp = new ErrorPopUp();

  private static final String AVAILABLE_STYLE = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
  private static final String UNAVAILABLE_STYLE = "-fx-background-color: #F44336; -fx-text-fill: white;";
  private static final String SELECTED_STYLE = "-fx-background-color: #2196F3; -fx-text-fill: white;";
  private static final String DEFAULT_STYLE = "";

  public mainViewUserController(mainViewUserModel viewModel,
      shopClient shopService)
  {
    this.viewModel = viewModel;
    this.shopModel = new ShopModel(shopService);
  }

  public void initialize()
  {
    timeButtons.put("8:00-9:00", firstHour);
    timeButtons.put("9:00-10:00", SecondHour);
    timeButtons.put("10:00-11:00", ThirdHour);
    timeButtons.put("11:00-12:00", FourthHour);
    timeButtons.put("12:00-13:00", FifthHour);
    timeButtons.put("13:00-14:00", SixthHour);
    timeButtons.put("14:00-15:00", SeventhHour);
    timeButtons.put("15:00-16:00", EightHour);

    datePicker.valueProperty()
        .bindBidirectional(viewModel.getSelectedDateProperty());

    datePicker.valueProperty().addListener((Observable, oldDate, newDate) -> {
      if (newDate != null)
      {
        updateTimeSlotButtons();
        clearSelectedTimeSlot();
      }
    });

    ConfirmBooking.setDisable(true);

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    productTable.setItems(shopModel.getProducts());
  }

  @FXML public void addToCart()
  {
    product selectedProduct = productTable.getSelectionModel()
        .getSelectedItem();
    if (selectedProduct == null)
    {
      errorPopUp.show("Error", "Please select a product");
      return;
    }

    shopModel.addToCart(selectedProduct.getProductName(), 1);
    productTable.refresh();
  }

  @FXML public void ConfirmPurchase()
  {
    shopModel.confirmPurchase();
    productTable.refresh();
  }

  private void updateTimeSlotButtons()
  {
    for (Map.Entry<String, Button> entry : timeButtons.entrySet())
    {
      String timeSlot = entry.getKey();
      Button button = entry.getValue();

      boolean isAvailable = viewModel.isTimeSlotAvailable(timeSlot);

      if (isAvailable)
      {
        button.setStyle(AVAILABLE_STYLE);
        button.setDisable(false);
      }
      else
      {
        button.setStyle(UNAVAILABLE_STYLE);
        button.setDisable(true);
      }
    }
  }

  private void clearSelectedTimeSlot()
  {
    selectedTimeSlot = null;
    viewModel.setSelectedTimeSlot(null);
    ConfirmBooking.setDisable(true);

    updateTimeSlotButtons();
  }

  private void selectTimeSlot(String timeSlot, Button button)
  {
    if (selectedTimeSlot != null)
    {
      Button prevButton = timeButtons.get(selectedTimeSlot);
      prevButton.setStyle(AVAILABLE_STYLE);
    }

    selectedTimeSlot = timeSlot;
    viewModel.setSelectedTimeSlot(timeSlot);
    button.setStyle(SELECTED_STYLE);
    ConfirmBooking.setDisable(false);
  }

  @FXML public void choosefirstHour()
  {
    selectTimeSlot("8:00-9:00", firstHour);
  }

  @FXML public void chooseSecondHour()
  {
    selectTimeSlot("9:00-10:00", SecondHour);
  }

  @FXML public void chooseThirdHour()
  {
    selectTimeSlot("10:00-11:00", ThirdHour);
  }

  @FXML public void chooseFourthHour()
  {
    selectTimeSlot("11:00-12:00", FourthHour);
  }

  @FXML public void chooseFifthHour()
  {
    selectTimeSlot("12:00-13:00", FifthHour);
  }

  @FXML public void chooseSixthHour()
  {
    selectTimeSlot("13:00-14:00", SixthHour);
  }

  @FXML public void chooseSeventhHour()
  {
    selectTimeSlot("14:00-15:00", SeventhHour);
  }

  @FXML public void chooseEightHour()
  {
    selectTimeSlot("15:00-16:00", EightHour);
  }

  @FXML public void confirmBooking()
  {
    if (datePicker.getValue() == null)
    {
      errorPopUp.show("Error", "Please select a date");
      return;
    }

    if (selectedTimeSlot == null)
    {
      errorPopUp.show("Error", "Please select a time slot");
      return;
    }

    boolean success = viewModel.bookAppointment();
    if (success)
    {
      clearSelectedTimeSlot();
      updateTimeSlotButtons();
    }
  }
}