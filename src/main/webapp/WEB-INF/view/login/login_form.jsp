<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<style>
    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @keyframes fadeOut {
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }

</style>
<script>
    window.addEventListener("DOMContentLoaded", function () {
        const loginForm = document.getElementById("loginForm");
        const alertMessage = document.getElementById("alertMessage");

        const validate = function (form) {
            form['user_id'].value
            form['user_password'].value
            if (form['user_id'].value.trim() === '') {
                showAlertMessage("회원 아이디를 다시 입력 해주세요.");
                form['user_id'].focus();
                return false;
            } else if (form['user_password'].value.trim() === '') {
                showAlertMessage("회원 아이디를 다시 입력 해주세요.");
                form['user_password'].focus();
                return false;
            }
            return true;
        }

        loginForm.addEventListener("submit", function (event) {
            if (!validate(event.target)) {
                event.preventDefault();
                return;
            }
            doLogin(loginForm['user_id'].value, loginForm['user_password'].value,function (loginSuccess){
                console.log(loginForm['user_id'].value);
                return;
            });
        });


        function doLogin(paramUserId, pramUserPassword, loginSuccess) {
            const xhr = new XMLHttpRequest();
            const url = "http://localhost:8080" + "/loginAction.do";

            const data = {
                user_id: paramUserId,
                user_password: pramUserPassword
            }
            xhr.addEventListener('load', function () {
                if (this.status === 200) {
                    console.log(this.response);
                    loginSuccess(this.response)
                }
            });
            xhr.open("POST", url);
            xhr.setRequestHeader("content-type", "application/json");
            xhr.responseType = "json";
            xhr.send(JSON.stringify(data));
        }

        function showAlertMessage(message) {
            alertMessage.innerHTML = message;
            alertMessage.style.animation = "fadeIn 1.0s";

            alertMessage.style.display = "block";

            setTimeout(function () {
                alertMessage.style.animation = "fadeOut 1.0s";
                setTimeout(function () {
                    alertMessage.style.display = "none";
                    alertMessage.style.opacity = 3;
                }, 500);
            }, 3000);
        }
    })
</script>
<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form id="loginForm" method="post" action="/loginAction.do">

            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating mb-3">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디">
                <label for="user_id">회원아이디</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호">
                <label for="user_password">비밀번호</label>
            </div>
            <div class="text-danger" id="alertMessage" style="display: none;"></div>

            <c:if test="${not empty error_message}">
                <p class="text-danger">${error_message}</p>
            </c:if>
            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign in</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>
        </form>
    </div>
</div>