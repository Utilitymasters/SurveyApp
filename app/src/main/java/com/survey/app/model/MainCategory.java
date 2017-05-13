package com.survey.app.model;

import java.util.ArrayList;

/**
 * Created by rohit.anvekar  on 5/11/2017.
 */
public class MainCategory {

	private String pName;

	private ArrayList<SubCategory> mSubCategoryList;

	public MainCategory(String pName, ArrayList<SubCategory> mSubCategoryList) {
		super();
		this.pName = pName;
		this.mSubCategoryList = mSubCategoryList;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public ArrayList<SubCategory> getmSubCategoryList() {
		return mSubCategoryList;
	}

	public void setmSubCategoryList(ArrayList<SubCategory> mSubCategoryList) {
		this.mSubCategoryList = mSubCategoryList;
	}


	public static class SubCategory {

		private String pSubCatName;
		private ArrayList<SubCategoryItemList> mSubCategoryItemListArray;

		public SubCategory(String pSubCatName,
				ArrayList<SubCategoryItemList> mSubCategoryItemListArray) {
			super();
			this.pSubCatName = pSubCatName;
			this.mSubCategoryItemListArray = mSubCategoryItemListArray;
		}

		public String getpSubCatName() {
			return pSubCatName;
		}

		public void setpSubCatName(String pSubCatName) {
			this.pSubCatName = pSubCatName;
		}

		public ArrayList<SubCategoryItemList> getmSubCategoryItemListArray() {
			return mSubCategoryItemListArray;
		}

		public void setmSubCategoryItemListArray(ArrayList<SubCategoryItemList> mSubCategoryItemListArray) {
			this.mSubCategoryItemListArray = mSubCategoryItemListArray;
		}


		public static class SubCategoryItemList {

			private String itemName;


			public SubCategoryItemList(String itemName, String itemPrice) {
				super();
				this.itemName = itemName;

			}

			public String getItemName() {
				return itemName;
			}

			public void setItemName(String itemName) {
				this.itemName = itemName;
			}


		}

	}

}
