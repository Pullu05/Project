<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<option value="0">Select State </option>                       
<c:forEach items="${StateList}" var="state">   
    <option value="${state.getStateId()}" <c:if test="${state.getStateId()==(User.getStateId())}"> selected </c:if>> 
        <c:out value="${state.getStateName()}"/>  
    </option>
</c:forEach>