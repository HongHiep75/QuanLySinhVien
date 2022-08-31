package model;

import java.util.Objects;

import chuc.nang.chung.HienThiTongTin;
import chuc.nang.chung.TinhDiem;
import danh.sach.DanhSachMon;

public class BangDiem implements TinhDiem, HienThiTongTin {

	private SinhVien sinhVien;
	private DanhSachMon danhSachMon = new DanhSachMon();
	private double[] bangDiem;

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public DanhSachMon getDanhSachMon() {
		return danhSachMon;
	}

	public double[] getBangDiem() {
		return bangDiem;
	}

	public int soMon() {
		return this.bangDiem.length;
	}

	private void setBangDiem(double diem) {
		if (this.bangDiem == null) {
			this.bangDiem = new double[1];
			this.bangDiem[0] = diem;
			return;
		}
		int n = this.bangDiem.length;
		int nTong = n + 1;
		double[] bangDiemMoi = new double[nTong];
		for (int i = 0; i < n; i++) {
			bangDiemMoi[i] = this.bangDiem[i];
		}
		bangDiemMoi[nTong - 1] = diem;
		this.bangDiem = bangDiemMoi;
	}

	public void themDiemVaoMon(MonHoc monMoi, double diem) {
		int viTri = this.danhSachMon.themMonHoc(monMoi);
		if (viTri < 0) {
			this.setBangDiem(diem);
		} else {
			this.bangDiem[viTri] = diem;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(sinhVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangDiem other = (BangDiem) obj;
		return Objects.equals(sinhVien, other.sinhVien);
	}

	@Override
	public void hienThongTin() {
		System.out.println(
				"- Mã sinh viên: " + this.sinhVien.getMaSinhVien() + " Họ và tên: " + this.sinhVien.getHoTen());
		if (this.danhSachMon == null || this.bangDiem == null)
			return;

		MonHoc[] monHocs = this.danhSachMon.getMonHocs();
		int soMon = monHocs.length;
		double[] listDiem = this.bangDiem;
		if (soMon != listDiem.length)
			return;
		for (int i = 0; i < soMon; i++) {
			System.out.println(" + Môn học: " + monHocs[i].getTenMon() + " - Điểm: " + listDiem[i]);
		}

	}

	@Override
	public double tinhDiem() {
		if (this.danhSachMon == null || this.bangDiem == null)
			return -1;
		double tongHocPhan = 0;
		MonHoc[] monHocs = this.danhSachMon.getMonHocs();
		int soMon = monHocs.length;
		double[] listDiem = this.bangDiem;
		if (soMon != listDiem.length)
			return -1;
		double diemQuyDoi = 0;
		for (int i = 0; i < soMon; i++) {
			tongHocPhan += monHocs[i].getDonViHocTrinh();
			diemQuyDoi += gpa(listDiem[i]) * monHocs[i].getDonViHocTrinh();
		}
		if (tongHocPhan <= 0) {
			return -1;
		}
		double gpa = diemQuyDoi / tongHocPhan;
		return Math.round(gpa * 100) / 100.0;
	}

	public double gpa(double diem) {
		double grade = 0.0;
		if (diem >= 8.5) {
			grade = 4.0;
			return grade;
		} else if (8.5 > diem && diem >= 7.5) {
			grade = 3.0;
			return grade;
		} else if (7.5 > diem && diem >= 6.0) {
			grade = 2.0;
			return grade;
		} else if (6.0 > diem && diem >= 4.0) {
			grade = 1.0;
			return grade;
		} else {
			return grade;
		}
	}

}
