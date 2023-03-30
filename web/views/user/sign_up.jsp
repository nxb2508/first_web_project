<%-- 
    Document   : sign_in
    Created on : Mar 30, 2023, 2:37:02 PM
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
    integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
    integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <title>Đăng Ký</title>
  <link rel="stylesheet" href="/ttcs/template/user/sign_up/css/styles.css">
</head>

<body>
  <div class="back-to-home">
        <a href="user_home">Trang Chủ</a>
  </div>
  <div class="container">
    <div class="row justify-content-center">
      <div class="inner-wrap col-xl-7">
        <div class="header">
          <h1>Tạo Tài Khoản Mới</h1>
        </div>
        <div class="data">
          <form action="user_sign_up" method="post" id="form" class="form">
            <div class="form-group">
              <input type="text" placeholder="Số điện thoại" class="form-control" id="phone-number" maxlength="11"
                name="phone_number" value="${requestScope.phone_number}">
              <span class="success-icon">
                <i class="fa-solid fa-check"></i>
              </span>
              <span class="error-icon">
                <i class="fa-solid fa-xmark"></i>
              </span>
              <small>Error</small>
            </div>
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control" id="email" name="email">
              <span class="success-icon">
                <i class="fa-solid fa-check"></i>
              </span>
              <span class="error-icon">
                <i class="fa-solid fa-xmark"></i>
              </span>
              <small id="email-existed">Error</small>
            </div>
            <div class="form-group">
              <input type="password" placeholder="Mật khẩu mới" class="form-control" id="password" name="password">
              <span class="success-icon">
                <i class="fa-solid fa-check"></i>
              </span>
              <span class="error-icon">
                <i class="fa-solid fa-xmark"></i>
              </span>
              <small>Error</small>
            </div>
            <div class="form-group">
              <input type="password" placeholder="Nhập lại mật khẩu mới" class="form-control" id="confirm-password">
              <span class="success-icon">
                <i class="fa-solid fa-check"></i>
              </span>
              <span class="error-icon">
                <i class="fa-solid fa-xmark"></i>
              </span>
              <small>Error</small>
            </div>
            <div class="form-group">
              <button class="btn form-control">Đăng ký</button>
            </div>
          </form>
        </div>
        <div class="sign-in">
          <a href="user_sign_in">Đã Có Tài Khoản</a>
        </div>
      </div>
    </div>
  </div>


  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>

  <script src="/ttcs/template/user/sign_up/js/main.js"></script>
  <script type="text/javascript">
    function deleteProduct(id) {
        if (confirm('Bạn sẽ đồng thời xóa những ảnh thuộc sản phẩm này?')) {
            window.location = 'delete_product?id=' + id;
        }
    }
    
    var isExisted = "${requestScope.isExisted}";
    console.log(isExisted)
    if(isExisted !== ""){
      errorMessage = document.getElementById("email-existed")
      errorMessage.innerText = isExisted
      errorMessage.classList.add("visible");
      email.focus()
    }
</script>
</body>

</html>