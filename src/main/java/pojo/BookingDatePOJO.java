package pojo;

public class BookingDatePOJO {
	
	private String checkin;
	private String checkout;
	
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	@Override
	public String toString() {
		return "BookingDatePOJO [checkin=" + checkin + ", checkout=" + checkout + "]";
	}	
	
	
}
