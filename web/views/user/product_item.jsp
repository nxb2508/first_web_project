<%-- 
    Document   : product
    Created on : Mar 26, 2023, 10:33:06 PM
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-3 col-md-4 col-sm-6 pb-1">
    <div class="product-item bg-light mb-4">
        <div class="product-img position-relative overflow-hidden">
            <img class="img-fluid w-100" src="<c:url value="/assets/images/${product.galeries[0].thumbnail}"/>" alt="">
            <div class="product-action">
                <a class="btn btn-outline-dark" href="user_add_to_cart?product_id=${product.id}&quantity=${1}"><i class="s-cart fa fa-shopping-cart"></i><span>Thêm Vào Giỏ Hàng</span></a>
            </div>
        </div>
        <div class="text-center py-4">
            <a class="h5 text-decoration-none text-truncate" href="<c:url value='/user_product_details?id=${product.id}'/>"><p class="d-flex justify-content-center align-items-center" style="white-space: break-spaces; word-break: break-word; min-height: 48px">${product.name}</p></a>
            <div class="d-flex align-items-center flex-column justify-content-center mt-2">
                <h5 style="color: #999900">
                    <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.price}" /> VNĐ</h5>
                <h6>Còn lại: ${product.quantity}</h6>
            </div>
        </div>
    </div>
</div>