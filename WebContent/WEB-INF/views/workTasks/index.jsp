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
        <h2>仕事用のタスク</h2>

        <h3>期限３日以内のタスク</h3>

        <table id="task_list">
            <tbody>
                <tr>
                    <th class="id">ID</th>
                    <th class="content">タスクの内容</th>
                    <th class="deadline">期限</th>
                </tr>
                <c:forEach var="workTask" items="${nearingWorkTasks}" varStatus="status">
                    <fmt:parseDate value="${workTask.deadline}" pattern="yyyy-MM-dd" var="day" type="date" />
                    <fmt:parseDate value="${workTask.deadline_time}"  var="time" type="time"  timeStyle="short" />

                    <tr class="row${status.count % 2}">
                        <td class="id"><a href="${pageContext.request.contextPath}/workShow?id=${workTask.id}"><c:out value="${workTask.id}" /></a></td>
                        <td class="content"><c:out value="${workTask.content}" /></td>
                        <td class="deadline">
                            <fmt:formatDate value='${day}' pattern='yyyy-MM-dd' />
                            <fmt:formatDate value='${time}'  type='time' timeStyle='short'  />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table><br>


        <h3>タスク一覧</h3>

        <p><a href="${pageContext.request.contextPath}/workSort">作成順に並び替え</a></p>

        <table id="task_list">
            <tbody>
                <tr>
                    <th class="id">ID</th>
                    <th class="content">タスクの内容</th>
                    <th class="deadline">期限</th>
                </tr>
                <c:forEach var="workTask" items="${workTasks}" varStatus="status">
                    <fmt:parseDate value="${workTask.deadline}" pattern="yyyy-MM-dd" var="day" type="date" />
                    <fmt:parseDate value="${workTask.deadline_time}"  var="time" type="time"  timeStyle="short" />

                    <tr class="row${status.count % 2}">
                        <td class="id"><a href="${pageContext.request.contextPath}/workShow?id=${workTask.id}"><c:out value="${workTask.id}" /></a></td>
                        <td class="content"><c:out value="${workTask.content}" /></td>
                        <td class="deadline">
                            <fmt:formatDate value='${day}' pattern='yyyy-MM-dd' />
                            <fmt:formatDate value='${time}'  type='time' timeStyle='short'  />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${workTasks_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((workTasks_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/workIndex?page=${i}"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="${pageContext.request.contextPath}/workNew">新規タスクの作成</a></p>
    </c:param>
</c:import>
