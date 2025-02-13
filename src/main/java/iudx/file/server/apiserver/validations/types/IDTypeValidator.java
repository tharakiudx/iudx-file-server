package iudx.file.server.apiserver.validations.types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import iudx.file.server.apiserver.exceptions.DxRuntimeException;
import iudx.file.server.apiserver.response.ResponseUrn;

public class IDTypeValidator implements Validator {

  private static final Logger LOGGER = LogManager.getLogger(IDTypeValidator.class);

  private final String value;
  private final boolean required;

  public IDTypeValidator(String value, boolean required) {
    this.value = value;
    this.required = required;
  }

  @Override
  public boolean isValid() {
    String message = "";
    if (required && (value == null || value.isBlank())) {
      message = "Validation error : null or blank value for required mandatory field";
    } else {
      if (value == null) {
        return true;
      }
      if(value.isBlank()) {
        message = "Validation error : blank value for passed";
      }
    }
    if(value!=null && !isValidLength(value)) {
      message = "Validation error: Value exceed max character limit";
    }
    if (value!= null &&  !isValidId(value)) {
      message = "Validation error : Invalid id [ "+value+" ]";
    }
    if(message.isBlank()) {
      return true;
    }
    throw new DxRuntimeException(failureCode(), ResponseUrn.INVALID_PAYLOAD_FORMAT, message);
  }

  @Override
  public int failureCode() {
    return 400;
  }

  @Override
  public String failureMessage() {
    return "Invalid id";
  }


  private boolean isValidId(String id) {
    String[] idcomponents = id.split("/");
    return idcomponents.length >= 4;
  }

  private boolean isValidLength(String id) {
    return id.length() <= 512;
  }
}
