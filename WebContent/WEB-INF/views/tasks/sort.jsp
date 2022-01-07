<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>期限３日以内のタスク</h2>

        <table id="task_list">
            <tbody>
                <tr>
                    <th class="id">ID</th>
                    <th class="content">タスクの内容</th>
                    <th class="deadline">期限</th>
                </tr>
                <c:forEach var="task" items="${nearingTasks}" varStatus="status">
                    <fmt:parseDate value="${task.deadline}" pattern="yyyy-MM-dd" var="day" type="date" />
                    <fmt:parseDate value="${task.deadline_time}"  var="time" type="time"  timeStyle="short" />

                    <tr class="row${status.count % 2}">
                        <td class="id"><a href="${pageContext.request.contextPath}/show?id=${task.id}"><c:out value="${task.id}" /></a></td>
                        <td class="content"><c:out value="${task.content}" /></td>
                        <td class="deadline">
                            <fmt:formatDate value='${day}' pattern='yyyy-MM-dd' />
                            <fmt:formatDate value='${time}'  type='time' timeStyle='short'  />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <h2>タスク一覧</h2>

        <p><a href="${pageContext.request.contextPath}/index">期限順に並び替え</a></p>

        <table id="task_list">
            <tbody>
                <tr>
                    <th class="id">ID</th>
                    <th class="content">タスクの内容</th>
                    <th class="deadline">期限</th>
                </tr>
                <c:forEach var="task" items="${tasks}">
                    <fmt:parseDate value="${task.deadline}" pattern="yyyy-MM-dd" var="day" type="date" />
                    <fmt:parseDate value="${task.deadline_time}"  var="time" type="time"  timeStyle="short" />

                    <tr class="row${tasks_count}%2">
                        <td class="id"><a href="${pageContext.request.contextPath}/show?id=${task.id}"><c:out value="${task.id}" /></a></td>
                        <td class="content"><c:out value="${task.content}" /></td>
                        <td class="deadline">
                            <fmt:formatDate value='${day}' pattern='yyyy-MM-dd' />
                            <fmt:formatDate value='${time}'  type='time' timeStyle='short'  />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${tasks_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((tasks_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/sort?page=${i}"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="${pageContext.request.contextPath}/new">新規タスクの作成</a></p>
    </c:param>
</c:import>
