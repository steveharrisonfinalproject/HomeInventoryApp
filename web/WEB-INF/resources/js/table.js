jQuery(document).ready(function(){
	$('#tableuser').dataTable();
	$('#tablecategory').dataTable();
	$(".btn[data-target='#myUserModal']").click(function() {
	       var columnHeadings = $("thead th").map(function() {
	                 return $(this).text();
	              }).get();
	       columnHeadings.pop();
	       var columnValues = $(this).parent().siblings().map(function() {
	                 return $(this).text();
	       }).get();
	  var modalBody = $('<div id="modalContent"></div>');
	  var modalForm = $('<form role="form" name="modalForm" action="/admin/updateuserpopup" method="post"></form>');
	  $.each(columnHeadings, function(i, columnHeader) {
	       var formGroup = $('<div class="form-group"></div>');
	       formGroup.append('<label for="'+columnHeader+'">'+columnHeader+'</label>');
	       if(columnHeader.includes("User")){
	    	   formGroup.append('<input class="form-control" name="'+columnHeader.trim()+'" id="'+columnHeader.trim()+'" value="'+columnValues[i]+'" disabled />');
	    	   formGroup.append('<input type="hidden" name="username" id="username" value="'+columnValues[i]+'" />');
	       }else {
	    	   formGroup.append('<input class="form-control" name="'+columnHeader.trim()+'" id="'+columnHeader.trim()+'" value="'+columnValues[i]+'" />');
	       }
	       if(columnHeader.includes("name") ||columnHeader.includes("Email")){
	    	   modalForm.append(formGroup);
	       }
	  });
	  modalBody.append(modalForm);
	  $('.modal-body').html(modalBody);
	});
	$(".btn[data-target='#editCategoryModal']").click(function() {
	       var columnHeadings = $("thead th").map(function() {
	                 return $(this).text();
	              }).get();
	       columnHeadings.pop();
	       var columnValues = $(this).parent().siblings().map(function() {
	                 return $(this).text();
	       }).get();
	  $.each(columnHeadings, function(i, columnHeader) {
	       if(columnHeader.includes("Id")){
	    	   $("#categoryidedit").val(columnValues[i]);
	       }
	       if(columnHeader.includes("name")){
	    	   $("#categorynameedit").val(columnValues[i]);
	       }
	  });
	});
	
	$(".btn[data-target='#myItemModal']").click(function() {
	       var columnHeadings = $("thead th").map(function() {
	                 return $(this).text();
	              }).get();
	       columnHeadings.pop();
	       var columnValues = $(this).parent().siblings().map(function() {
	                 return $(this).text();
	       }).get();
	  $.each(columnHeadings, function(i, columnHeader) {
//	       var formGroup = $('<div class="form-group"></div>');
//	       if(columnHeader.includes("Name") || columnHeader.includes("Price") || columnHeader.includes("Id")){
//	    	   formGroup.append('<label for="'+columnHeader+'">'+columnHeader+'</label>');
//	       }
	       if(columnHeader.includes("Id")){
	    	   $("#itemidedit").val(columnValues[i]);
	       }
	       if(columnHeader.includes("Name")){
	    	   $("#itemnameedit").val(columnValues[i]);
	       }
	       if(columnHeader.includes("Price")){
	    	   $("#priceedit").val(columnValues[i]);
	       }
	  });
	});
	$('.modal-footer.user .btn-primary').click(function() {
	   $('form[name="modalForm"]').submit();
	});
	$('.modal-footer.category .btn-primary').click(function() {
	   $('form[name="modalFormCategory"]').submit();
	});
	$('.modal-footer.add .btn-primary').click(function() {
	   $('form[name="modalFormAddCategory"]').submit();
	});
});