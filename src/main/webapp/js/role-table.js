$(document).ready(function() {
	//logic code
	// khi nội dung html load xong thì thực hiện code 
	//alert("test");
	$(".btn-xoa").click(function() {
		//this đại diện cho id đang muốn Delete
		var id = $(this).attr("id-role")
		var This = $(this)
		//Gọi đường dẫn và lấy dữ liệu từ đường dẫn đó trả ra
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/helloservlet/api/role?id=" + id,
			//data: { name: "John", location: "Boston" }   chỉ dành cho phương thức POST, tham số truyền ngầm
		})
			.done(function(result) {
				if(result.data == true) {
					This.closest("tr").remove()
				}
				console.log(result)
			});
	})
})