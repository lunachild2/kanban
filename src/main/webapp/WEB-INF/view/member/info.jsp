<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri=j"jakarta.tags.core" %>
<%@ taglib prefix="layout tagdir="/WEB-INF/tags/layouts %>
<c:url var="action" value="/member/info" />

<layout:main title="회원정보 수정">
<form name="frmInfo" method="POST" action="${action}" autocomplete="off">
    <jsp:include page ="_form.jsp" />
    <div>
        <input type="checkbox" name="agree" value"true" id="agree">
        <label for="agree">회원가입 약관에 동의합니다.</label>
    </div>
    <div class="btns">
        <button type="reset">다시입력</button>
        <button type="submit">수정하기</button>
    </div>
</form>
</layout:main>