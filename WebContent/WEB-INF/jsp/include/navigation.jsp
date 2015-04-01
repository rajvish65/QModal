<div class="vd_navbar vd_nav-width vd_navbar-email vd_bg-black-80 vd_navbar-left vd_navbar-style-2 " style="width: 15%;">

	<div class="navbar-menu clearfix">
    	<h3 class="menu-title hide-nav-medium hide-nav-small"><a href="create_modal.htm" class="btn vd_btn vd_bg-red">Add New Modal</a></h3>
  <div class="vd_menu">
   <ul id="modal_type">
    <c:forEach items="${modals}" var="modal">
	  <li class="item">
	  	<a href="" id="${modal.type}">
	  	<span class="menu-text"><c:out value="${modal.type}"></c:out></span>  
	  	</a>
	  </li>
	</c:forEach>                                        
</ul>
</div>

 </div>  

</div>

