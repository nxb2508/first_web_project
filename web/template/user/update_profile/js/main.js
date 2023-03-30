const form = document.getElementById("form");
const phoneNumber = document.getElementById("phone-number");

const phoneNumberRegex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/g;

const isPhoneNumber = (phoneNumber) => {
  return phoneNumber.match(phoneNumberRegex)
}

const setSuccessFor = (input) => {
  const formGroup = input.parentElement;
  const errorIcon = formGroup.querySelector(".error-icon");
  const successIcon = formGroup.querySelector(".success-icon");
  const errorMessage = formGroup.lastElementChild;

  if (input.classList.contains("error")) {
    input.classList.remove("error");
  }

  if (!input.classList.contains("success")) {
    input.classList.add("success");
  }

  if (errorIcon.classList.contains("visible")) {
    errorIcon.classList.remove("visible");
  }

  if (!successIcon.classList.contains("visible")) {
    successIcon.classList.add("visible");
  }

  errorMessage.classList.remove("visible");

}

const setErrorFor = (input, message) => {
  const formGroup = input.parentElement;
  const errorIcon = formGroup.querySelector(".error-icon");
  const successIcon = formGroup.querySelector(".success-icon");
  const errorMessage = formGroup.lastElementChild;

  if (input.classList.contains("success")) {
    input.classList.remove("success");
  }

  if (!input.classList.contains("error")) {
    input.classList.add("error");
  }

  if (!errorIcon.classList.contains("visible")) {
    errorIcon.classList.add("visible")
  }

  if (successIcon.classList.contains("visible")) {
    successIcon.classList.remove("visible")
  }


  errorMessage.innerText = message;
  errorMessage.classList.add("visible");
}

const setDefaultFor = (input) => {
  const formGroup = input.parentElement;
  const errorIcon = formGroup.querySelector(".error-icon");
  const successIcon = formGroup.querySelector(".success-icon");
  const errorMessage = formGroup.lastElementChild;

  if (input.classList.contains("success")) {
    input.classList.remove("success");
  }

  if (input.classList.contains("error")) {
    input.classList.remove("error");
  }

  if (errorIcon.classList.contains("visible")) {
    errorIcon.classList.remove("visible")
  }

  if (successIcon.classList.contains("visible")) {
    successIcon.classList.remove("visible")
  }

  if (errorMessage.classList.contains("visible")) {
    errorMessage.classList.remove("visible");
  }
}

form.addEventListener("submit", (evt) => {
  evt.preventDefault();
  checkInputs();
})

const checkInputs = () => {
  const phoneNumberValue = phoneNumber.value.trim()

  if(!isPhoneNumber(phoneNumberValue)) {
    setErrorFor(phoneNumber, "Vui lòng nhập số điện thoại đúng định dạng!");
    phoneNumber.focus();
  } else {
    form.submit();
  }
}

phoneNumber.addEventListener("blur", () => {
  const phoneNumberValue = phoneNumber.value.trim()
  if (isPhoneNumber(phoneNumberValue)) {
    setSuccessFor(phoneNumber)
  } else {
    setErrorFor(phoneNumber, "Vui lòng nhập số điện thoại đúng định dạng!");
    phoneNumber.focus();
  }
})

phoneNumber.addEventListener("input", () => {
  setDefaultFor(phoneNumber)
})