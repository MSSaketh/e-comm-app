package com.capgemini.productcart.domain;

public class CartItem {

	
	private String ItemId;
	private String ItemName;
	private float ItemPrice;

//	private String currency;
	public CartItem() {
		super();
	}

	public CartItem(String itemId, String itemName, float itemPrice) {
		super();
		ItemId = itemId;
		ItemName = itemName;
		ItemPrice = itemPrice;
	}

	public String getItemId() {
		return ItemId;
	}

	public void setItemId(String itemId) {
		ItemId = itemId;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public float getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(float itemPrice) {
		ItemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "CartItem [ItemId=" + ItemId + ", ItemName=" + ItemName + ", ItemPrice=" + ItemPrice + "]";
	}

	
	
}
