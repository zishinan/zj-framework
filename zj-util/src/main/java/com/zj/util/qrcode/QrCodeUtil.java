package com.zj.util.qrcode;


/**
 * @author <a href="xi.yang@i-jia.net">yangxi</a>
 */
public class QrCodeUtil {
	public static final String SEPARATOR = String.valueOf((char) 29);
	public static final int TYPE_COMMODITY = 1;
	public static final int TYPE_USER = 2;
	public static final int TYPE_CUSTOMER = 3;
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 获取商品二维码
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param shopId
	 * @param brandId
	 * @param commodityId
	 * @return
	 */
	public static String getCommodityCode(int commodityId) {
		return TYPE_COMMODITY+SEPARATOR+commodityId;
	}
	
	public static String getCustomerCode(int addressId) {
		return TYPE_CUSTOMER+SEPARATOR+addressId;
	}

	
}
