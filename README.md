### CINEVERSE CINEMA

Chương trình quản lý rạp chiếu phim CINEVERSE CINEMA

Tác giả: 
- Huỳnh Ngọc Hiệu - B9D53 C500
- Nguyễn Văn Thành An - B9D53 C500

#### Giới thiệu
Chương trình được viết bằng ngôn ngữ Java dùng cho mục đích học tập với các chức năng và giao diện phù hợp cho việc quản lý một rạp chiếu phim đơn giản.
Các đối tượng quản lý của chương trình bao gồm phòng chiếu, ghế, phim, suất chiếu, khách hàng, vé đã bán và doanh thu theo các đối tượng phim, suất chiếu và phòng.
Mỗi cửa sổ của chương trình có các chức năng hiển thị, thêm, cập nhật, xóa, sắp xếp, tìm kiếm theo các trường thông tin khác nhau giúp thuận tiện cho việc theo dõi tình hình rạp phim

#### Hướng dẫn sử dụng
##### Đăng nhập
Khi khởi chạy chương trình, cửa sổ đăng nhập sẽ xuất hiện. Người dùng với vai trò là quản trị viên nhập tên và mật khẩu đăng nhập (tên mặc định là "admin", mật khẩu "admin")
Nếu nhập sai sẽ hiện thông báo sai tên hoặc mật khẩu
##### Trang chủ
Khi đăng nhập thành công, cửa sổ đầu tiên xuất hiện là Trang chủ

***Góc trên bên phải là nút reload có chức năng tải lại chương trình***

Trang chủ hiện thị danh sách cuộn các phim đang có tại rạp kèm poster, danh sách cuộn các suất chiếu đang có, danh sách các phòng chiếu đang có

Mỗi ô suất chiếu bao gồm tên phim, thời gian chiếu, phòng chiếu, số ghế được đặt trên tổng số ghế.

Mỗi ô phòng chiếu bao gồm id phòng, sức chứa, phim đang chiếu hoặc sắp chiếu, tình trạng đang chiếu hay đang rảnh. Khi đến thời điểm chiếu hoặc hết thời lượng phim của suất chiếu, sau khi nhấn reload trạng thái phòng sẽ được thay đối.
##### Quản lý phòng
Cửa sổ Quản lý phòng bao gồm một bảng thông tin các phòng đã tạo bao gồm các trường id, số lượng ghế các loại, tổng số ghế, sức chứa (tổng số khách có thể chứa, chú ý ghế đôi có thể chứa được 2 khách), phim đang chiếu hoặc sắp chiếu và tình trạng phòng.

Vùng chức năng gồm các ô nhập dữ liệu phòng và các nút chức năng. Nút sắp xếp có thể sắp xếp theo các trường được chọn thông tin trong menu sổ, ô chọn để xác định chiều sắp xếp (không chọn là giảm dần)
Khi cần cập nhật phòng nào, cần click vào hàng của phòng trong bảng để cấc ô nhập dữ liệu nhận thông tin từ phòng rồi bắt đầu sửa. Sau khi sửa, nhấn nút cập nhật để lưu.
Khi thêm phòng, cần làm trống các ô dữ liệu trước khi nhập thông tin phòng mới
Khi xóa, click chọn và nhấn xóa.
##### Quản lý phim 
Cửa sổ Quản lý phim bao gồm một bảng thông tin các phim đã thêm bao gồm các trường id, tên phim, thể loại, độ tuổi được phép xem, thời lượng và ngày khởi chiếu

Vùng chức năng gồm các ô nhập dữ liệu tương tự như Quản lý phòng

Vùng tìm kiếm, có thể tìm kiếm theo các trường trong menu sổ, nhập vào ô nhập dữ liệu tìm kiếm một phần hoặc toàn bộ thông tin cần tìm
##### Quản lý suất chiếu
Cửa sổ Quản lý suất chiếu bao gồm một bảng thông tin các suất chiếu đã tạo bao gồm các trường id, tên phim, phòng, thời gian chiếu, số ghế đã đặt trên tổng số ghế.

Vùng chức năng gồm các ô nhập dữ liệu tương tự như Quản lý phòng. Riêng ô nhập thời gian chiếu, cần chọn ngày trong khung lịch hiển thị sẵn, chọn giờ và phút từ spinner. nhấn xác nhận để hiện thị thời gian đã chọn.

Khi thêm suất chiếu cần lưu ý không đặt thời gian chiếu hoặc kết thúc trùng với thời gian của suất khác, không đặt thời gian trong quá khứ và không bỏ trống ô thời gian chiếu. Khi vi phạm các điều trên, sẽ có thông báo nhắc nhở.

Khi đã hết thời gian chiếu của suất, sau khi nhấn reload hay khi đăng nhập vào suất chiếu sẽ được tự động xóa.

Vùng sắp xếp có thể sắp xếp theo id, thời gian chiếu, phần trăm ghế đã đặt. Nếu chọn tăng dần để sắp xếp theo thời gian chiếu, suất chiếu đầu tiên sẽ là suất chiếu sớm nhất.
##### Quản lý thành viên
Cửa sổ Quản lý thành viên bao gồm một bảng thông tin các khách được thêm vào làm thành viên bao gồm các trường id, họ tên, ngày sinh, giới tính, số lượng vé đã đặt, tổng số tiền đã thanh toán và điểm đã chọn lũy (điểm này có thể dùng để giảm giá vé khi đặt).

Vùng chức năng gồm các ô nhập dữ liệu tương tự như Quản lý suất chiếu.

Vùng tìm kiếm tương tự như vùng tìm kiếm trong Quản lý phim
##### Quản lý vé
Cửa sổ Quản lý vé bao gồm một bảng thông tin các vé đã được bán bao gồm các trường id, họ tên khách, vị trí, loại ghế, tên phim, phòng chiếu, thời gian chiếu.

Vùng chức năng bao gồm sắp xếp tên khách và tên phim theo A-Z, xóa vé

Vùng tìm kiếm tương tự như vùng tìm kiếm trong Quản lý phim.

Dữ liệu các vé đã bán sẽ không bị mất khi suất chiếu kết thúc.
##### Đặt vé
Cửa sổ đặt vé bao gồm bảng thông tin suất chiếu hiện có tương tự như Quản lý suất chiếu. Bảng Các suất chiếu hiện có chỉ hiển thị các suất chiếu chưa được chiếu hoặc đã chiếu được 20 phút (thời gian cho phép đặt vé).

Vùng chức năng bao gồm các ô nhận thông tin suất chiếu khi chọn, checkbox xác định thành viên đặt vé hoặc khách vãng lai, menu các phim có suất chiếu.

Khi đặt vé, cần xác định khách vãng lai đặt hoặc thành viên đặt. Nếu là thành viên đặt thì chọn thành viên từ menu sổ.
Sau đó lọc phim muốn xem theo menu sổ, chọn suất chiếu trong bảng để các ô dữ liệu hiện thông tin suất chiếu được nhập thông tin. 
Tiếp theo, nhấn vào Chọn ghế để chọn ghế. Sau khi chọn ghế, nhấn đặt vé. Một bảng hội thoại Thông tin vé sẽ hiện ra.
Hội thoại Thông tin vé gồm thông tin về khách đặt (nếu là khách vãng lai thì tên và id sẽ là "no_mem" - no member, hệ thống sẽ ghi nhận thông tin vé của khách vãng lai nhưng không ghi nhận đối tượng khách vãng lai), thông tin suất chiếu, điểm chọn lũy thành viên được cộng nếu đặt vé, khung Sử dụng điểm chọn lũy, tổng số tiền thanh toán.
Chỉ có thành viên của rạp phim mới được sử dụng khung Sử dụng điểm chọn lũy. Khi nhấn chọn sử dụng, số điểm chọn lũy của thành viên sẽ hiện ra, nhập số điểm muốn sử dụng vào ô nhập và nhấn Giảm giá. Nếu thành công số tiền thanh toán sẽ thay đổi.
Khi đã sử dụng xong điểm chọn lũy nhấn Xác nhận thanh toán.

Sau khi đặt vé, dữ liệu trong phần Doanh thu sẽ tự động cập nhật
##### Doanh thu
Cửa sổ doanh thu bao gồm các vùng tổng doanh thu, doanh thu theo phim, doanh thu theo suất chiếu và doanh thu theo phòng.

Vùng doanh thu theo phim và suất chiếu được thiết kế tương tự nhau với danh sách cuộn các ô thông tin phim và suất chiếu kèm poster. Nút sắp xếp có thể sắp xếp các phim hoặc suất chiếu theo doanh thu kiếm được theo chiếu tăng hay giảm dần.

Vùng doanh thu theo phòng gồm một bảng có các trường id, sức chứa, số lượng vé đã đặt và doanh thu từng phòng. Chức năng gồm có sắp xếp và tìm kiếm phòng theo khoảng doanh thu

Góc trên bên phải là nút Đặt lại doanh thu. Khi nhấn tất cả các giá trị doanh thu sẽ về 0, tất cả các vé sẽ bị xóa.

***Tất cả các bảng trong chương trình có thể thực hiện sắp xếp các đối tượng theo trường thông tin bằng cách nhấn vào tiêu đề cột được dùng làm tiêu chí sắp xếp đến khi xuất hiện tam giác xuôi hay ngược***

### THANKS FOR USING
### HAPPY CODING
