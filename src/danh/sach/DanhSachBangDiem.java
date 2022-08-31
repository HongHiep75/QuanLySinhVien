package danh.sach;

import java.util.Scanner;

import chuc.nang.chung.HienThiTongTin;
import model.BangDiem;
import model.MonHoc;
import model.SinhVien;
import quan.ly.file.FileIODanhSachBangDiem;
import thuc.thi.chuong.trinh.QuanLySinhVien;

public class DanhSachBangDiem implements HienThiTongTin {

	private BangDiem[] listBangDiem;

	public BangDiem[] getListBangDiem() {
		return listBangDiem;
	}

	public void setListBangDiem(BangDiem[] bangDiem) {
		this.listBangDiem = bangDiem;
	}

	public void setBangDiem(BangDiem bangDiem) {
		if (this.listBangDiem == null) {
			this.listBangDiem = new BangDiem[1];
			this.listBangDiem[0] = bangDiem;
			return;
		}
		int n = this.listBangDiem.length;
		int nTong = n + 1;
		BangDiem[] bangMoi = new BangDiem[nTong];
		for (int i = 0; i < n; i++) {
			// nếu tìm thấy bang điểm đã tồn tại ta them từng mon và điểm tương ứng vào bảng
			// diểm đã có
			if (this.listBangDiem[i].equals(bangDiem)) {
				MonHoc[] danhSachMOn = bangDiem.getDanhSachMon().getMonHocs();
				int soMon = danhSachMOn.length;
				for (int j = 0; j < soMon; j++) {
					this.listBangDiem[i].themDiemVaoMon(danhSachMOn[j], bangDiem.getBangDiem()[j]);
				}

				return;
			}
			bangMoi[i] = this.listBangDiem[i];
		}
		bangMoi[nTong - 1] = bangDiem;
		this.listBangDiem = bangMoi;
	}

	@Override
	public void hienThongTin() {
		if (this.listBangDiem == null)
			return;
		BangDiem[] bangDiems = this.listBangDiem;
		int n = bangDiems.length;
		for (int i = 0; i < n; i++) {
			bangDiems[i].hienThongTin();
		}
	}

	public void sapXepTheoTenSinhVien() {
		if (this.listBangDiem == null)
			return;
		int n = this.listBangDiem.length;
		String[] danhSachTen = new String[n];

		for (int i = 0; i < n; i++) {
			danhSachTen[i] = this.listBangDiem[i].getSinhVien().getHoTen();
		}

		for (int i = 0; i < n - 1; i++) {

			for (int j = 1; j < n; j++) {
				if (danhSachTen[j - 1].compareTo(danhSachTen[j]) > 0) {
					String trungGian = danhSachTen[j - 1];
					danhSachTen[j - 1] = danhSachTen[j];
					danhSachTen[j] = trungGian;

					BangDiem temp = this.listBangDiem[j - 1];
					this.listBangDiem[j - 1] = this.listBangDiem[j];
					this.listBangDiem[j] = temp;
				}
			}
		}

	}

	public void sapXepTheoTenMon() {
		if (this.listBangDiem == null)
			return;
		int n = this.listBangDiem.length;
		for (int i = 0; i < n; i++) {
			thucHienSapXep(this.listBangDiem[i]);
		}
	}

	private void thucHienSapXep(BangDiem bangDiem) {
		MonHoc[] danhSachMonHoc = bangDiem.getDanhSachMon().getMonHocs();
		double[] danhSachDiem = bangDiem.getBangDiem();
		int n = danhSachMonHoc.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 1; j < n; j++) {
				if (danhSachMonHoc[j - 1].getTenMon().compareTo(danhSachMonHoc[j].getTenMon()) > 0) {
					MonHoc trungGian = danhSachMonHoc[j - 1];
					danhSachMonHoc[j - 1] = danhSachMonHoc[j];
					danhSachMonHoc[j] = trungGian;

					double temp = danhSachDiem[j - 1];
					danhSachDiem[j - 1] = danhSachDiem[j];
					danhSachDiem[j] = temp;
				}
			}
		}
	}

	public void hienThiDiem() {
		if (this.listBangDiem == null)
			return;
		int n = this.listBangDiem.length;
		SinhVien sinhVien;
		for (int i = 0; i < n; i++) {
			sinhVien = this.listBangDiem[i].getSinhVien();
			System.out.println("Mã sinh viên: " + sinhVien.getMaSinhVien() + "- Họ và tên: " + sinhVien.getHoTen());
			System.out.println("GPA: " + this.listBangDiem[i].tinhDiem());
		}

	}

	// them thong tin vao bang diem
	public void themmBangDiem(Scanner sc, DanhSachSinhVien danhSachSinhVien, DanhSachMon danhSachMon) {
		if (danhSachSinhVien.getSinhVien() == null || danhSachMon.getMonHocs() == null) {
			System.out.println("Danh sách sinh viên hoặc danh sách môn trống");
			return;
		}
		System.out.println("Chọn sinh Vien bằng cách nhập mã sinh viên");
		int maSinhVien = QuanLySinhVien.kiemTraDauVaoInt(sc);
		SinhVien sinhVien = danhSachSinhVien.timSinhVien(maSinhVien);

		while (sinhVien == null) {
			System.out.println("Sinh vien không tồn tại nhập lại mã");
			maSinhVien = QuanLySinhVien.kiemTraDauVaoInt(sc);
			sinhVien = danhSachSinhVien.timSinhVien(maSinhVien);
		}
		BangDiem bangDiem = this.timBangDiemTSV(sinhVien);
		bangDiem.setSinhVien(sinhVien);
		System.out.println("Thêm điểm cho sinh viên " + sinhVien.getHoTen());
		// them diem va vào môn học tương ứng
		System.out.println("Chọn số môn có điểm: ");
		int tongMon = danhSachMon.getMonHocs().length;
		int soMonThemDiem = QuanLySinhVien.kiemTraDauVaoInt(sc);
		while (soMonThemDiem > tongMon || soMonThemDiem < 1) {
			System.out.println("Số môn không hợp lớn hơn số môn hiện tại và cần nhập ít nhất 1 môn: ");
			soMonThemDiem = QuanLySinhVien.kiemTraDauVaoInt(sc);

		}
		int maMon;
		double diem;
		for (int i = 0; i < soMonThemDiem; i++) {
			System.out.println("Nhập mã môn: ");
			maMon = QuanLySinhVien.kiemTraDauVaoInt(sc);
			MonHoc monHoc = danhSachMon.timMonHoc(maMon);
			while (monHoc == null) {
				System.out.println("Nhập mã môn không đúng: ");
				maMon = QuanLySinhVien.kiemTraDauVaoInt(sc);
				monHoc = danhSachMon.timMonHoc(maMon);
			}
			System.out.println("Nhập điểm: ");
			diem = QuanLySinhVien.kiemTraDiem(sc);
			bangDiem.themDiemVaoMon(monHoc, diem);

		}
		this.setBangDiem(bangDiem);
		boolean chech = true;
		chech = FileIODanhSachBangDiem.writeFileDanhSachBangDiem(this.getListBangDiem());
		if (chech) {
			this.hienThongTin();
		} else {
			System.out.println("Không lưu được file ");
		}

	}

	public BangDiem timBangDiemTSV(SinhVien sinhVien) {
		if (this.listBangDiem == null) {
			return new BangDiem();
		}
		int n = this.listBangDiem.length;
		for (int i = 0; i < n; i++) {
			if (this.listBangDiem[i].getSinhVien().equals(sinhVien)) {
				return this.listBangDiem[i];
			}
		}

		return new BangDiem();
	}

	public void sapXep(Scanner sc) {
		System.out.println("Chọn kiểu sắp xếp");
		System.out.println("1. Theo họ tên sinh vien");
		System.out.println("2.Theo số tên môn học");
		int chon = sc.nextInt();
		while (chon < 1 || chon > 2) {
			System.out.println("Vui lòng chọn lại");
			chon = sc.nextInt();
		}
		if (chon == 1) {
			if (this.listBangDiem == null) {
				System.out.println("Danh sách bảng điểm trống\n");
				return;
			}
			this.sapXepTheoTenSinhVien();
			this.hienThongTin();
		} else {
			this.sapXepTheoTenMon();
			this.hienThongTin();

		}
	}

}
