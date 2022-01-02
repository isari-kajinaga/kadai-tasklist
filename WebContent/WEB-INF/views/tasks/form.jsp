<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<fmt:parseDate value="${task.deadline}" pattern="yyyy-MM-dd" var="day" type="date" />
<label for="deadline">期限年月日</label><br />
<input type="date" name="deadline" value="<fmt:formatDate value='${day}' pattern='yyyy-MM-dd' />" />
<br /><br />

<fmt:parseDate value="${task.deadline_time}" pattern="HH:mm" var="time" type="time" />
<label for="deadline_time">期限時刻</label><br />
<input type="time" name="deadline_time" value="<fmt:formatDate value='${time}' pattern='HH:mm' />" />
<br /><br />

<label for="content">タスクの内容</label><br />
<input type="text" name="content" value="${task.content}" />

<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">作成</button>