package model;

import java.util.Objects;

import chuc.nang.chung.HienThiTongTin;

public class MonHoc implements HienThiTongTin {
	private static final int MIN_AUTO = 10000;
	private static int Auto = MIN_AUTO;
	private int maMon;
	private String tenMon;
	private double donViHocTrinh;
	private String loaiMon;

	public MonHoc(String tenMon, double donViHocTrinh, String loaiMon) {

		this.maMon = Auto++;
		this.tenMon = tenMon;
		this.donViHocTrinh = donViHocTrinh;
		this.loaiMon = loaiMon;
	}

	public MonHoc(int maMon, String tenMon, double donViHocTrinh, String loaiMon) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.donViHocTrinh = donViHocTrinh;
		this.loaiMon = loaiMon;
	}

	public static void setAuto(int auto) {
		if (MIN_AUTO > auto || auto > MIN_AUTO * 10) {
			System.out.println("Thêm không thành công");
			return;
		} else {
			Auto = auto;
		}
	}

	public static int getAuto() {
		return Auto;
	}

	public String getTenMon() {
		return tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public double getDonViHocTrinh() {
		return donViHocTrinh;
	}

	public void setDonViHocTrinh(double donViHocTrinh) {
		this.donViHocTrinh = donViHocTrinh;
	}

	public String getLoaiMon() {
		return loaiMon;
	}

	public void setLoaiMon(String loaiMon) {
		this.loaiMon = loaiMon;
	}

	public int getMaMon() {
		return maMon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maMon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonHoc other = (MonHoc) obj;
		return maMon == other.maMon;
	}

	@Override
	public void hienThongTin() {
		StringBuilder builder = new StringBuilder();
		builder.append("+ Mã môn: ");
		builder.append(this.maMon);
		builder.append(" - Tên môn: ");
		builder.append(this.tenMon);
		builder.append(" - Số đơn vị học trình: ");
		builder.append(this.donViHocTrinh);
		builder.append(" - Loại môn: ");
		builder.append(this.loaiMon);
		System.out.println(builder.toString());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.maMon);
		builder.append("-");
		builder.append(this.tenMon);
		builder.append("-");
		builder.append(this.donViHocTrinh);
		builder.append("-");
		builder.append(this.loaiMon);

		return builder.toString();
	}

}
