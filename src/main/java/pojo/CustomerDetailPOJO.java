package pojo;

public class CustomerDetailPOJO {
	private int bookingid;
	private BookingDetailPOJO booking;
	
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public BookingDetailPOJO getBooking() {
		return booking;
	}
	public void setBooking(BookingDetailPOJO booking) {
		this.booking = booking;
	}
	
	@Override
	public String toString() {
		return "CustomerDetailPOJO [bookingid=" + bookingid + ", booking=" + booking.toString() + "]";
	}	
}
