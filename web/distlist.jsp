<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<option value="0">Select district </option>
<c:forEach items="${DistictList}" var="distict">   
    <option value="${distict.getDistictId()}" > <c:out value="${distict.getDistictName()}"/>  </option>
</c:forEach>
