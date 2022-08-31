package model;

import java.util.Objects;

import chuc.nang.chung.HienThiTongTin;

public class SinhVien implements HienThiTongTin {
	private static final int MIN_AUTO = 10000;
	private static int Auto = 10000;
	private int maSinhVien;
	private String hoTen, diaChi;
	private String soDienThoai;
	private String lop;

	public SinhVien(String hoTen, String diaChi, String soDienThoai, String lop) {
		super();
		this.maSinhVien = Auto++;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.lop = lop;
	}

	public SinhVien(int maSinhVien, String hoTen, String diaChi, String soDienThoai, String lop) {
		super();
		this.maSinhVien = maSinhVien;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.lop = lop;
	}

	public static int getAuto() {
		return Auto;
	}

	public static void setAuto(int auto) {
		if (MIN_AUTO > auto || auto > MIN_AUTO * 10) {
			System.out.println("Thêm không thành công");
			return;
		} else {
			Auto = auto;
		}
	}

	public SinhVien() {
	};

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public int getMaSinhVien() {
		return maSinhVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSinhVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		return maSinhVien == other.maSinhVien;
	}

	@Override
	public void hienThongTin() {
		StringBuilder builder = new StringBuilder();
		builder.append("- maSV: ");
		builder.append(this.maSinhVien);
		builder.append(" - Họ và tên: ");
		builder.append(this.hoTen);
		builder.append(" - Địa chỉ: ");
		builder.append(this.diaChi);

		builder.append(" - Số điện thoại: ");
		builder.append(this.soDienThoai);
		builder.append(" - Lớp: ");
		builder.append(this.lop);
		System.out.println(builder.toString());

	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append(this.maSinhVien);
		builder.append("-");
		builder.append(this.hoTen);
		builder.append("-");
		builder.append(this.diaChi);
		builder.append("-");
		builder.append(this.soDienThoai);
		builder.append("-");
		builder.append(this.lop);

		return builder.toString();
	}

}
