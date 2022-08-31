package danh.sach;

import java.util.Scanner;

import chuc.nang.chung.HienThiTongTin;
import model.MonHoc;
import model.SinhVien;
import quan.ly.file.FileIODanhSachMonHoc;
import thuc.thi.chuong.trinh.QuanLySinhVien;

public class DanhSachMon implements HienThiTongTin {

	private MonHoc[] monHocs;

	public MonHoc[] getMonHocs() {
		return monHocs;
	}

	public void setMonHocs(MonHoc[] monHocs) {
		if (monHocs == null)
			return;
		if (this.monHocs == null) {
			this.monHocs = monHocs;
		} else {
			int lenghtDanhSachSach = this.monHocs.length;
			int leghtListSach = monHocs.length;
			int lenghtTong = lenghtDanhSachSach + leghtListSach;
			MonHoc[] danhSachMoi = new MonHoc[lenghtTong];
			int n = 0;
			int m = 0;
			boolean ckeckDanhSach1 = true;
			boolean ckeckDanhSach2 = true;
			int trungGian = lenghtDanhSachSach;
			while (ckeckDanhSach1 || ckeckDanhSach2) {
				if (n == lenghtDanhSachSach) {
					ckeckDanhSach1 = false;
				} else {
					danhSachMoi[n] = this.monHocs[n];
					n++;
				}

				if (m == leghtListSach) {
					ckeckDanhSach2 = false;
				} else {
					danhSachMoi[trungGian] = monHocs[m];
					m++;
					trungGian++;
				}
			}

			this.monHocs = danhSachMoi;
		}
	}

	public int themMonHoc(MonHoc monMoi) {
		if (monMoi == null)
			return -1;
		int viTri = -1;
		if (this.monHocs == null) {
			this.monHocs = new MonHoc[1];
			this.monHocs[0] = monMoi;
			return viTri;
		}
		int n = this.monHocs.length;
		int nTong = n + 1;
		MonHoc[] bangMoi = new MonHoc[nTong];
		for (int i = 0; i < n; i++) {
			if (this.monHocs[i].equals(monMoi)) {
				return i;
			}
			if (this.monHocs[i] == null) {
				this.monHocs[i] = monMoi;
				return i;
			}
			bangMoi[i] = this.monHocs[i];
		}
		bangMoi[nTong - 1] = monMoi;
		this.monHocs = bangMoi;
		return viTri;

	}

	@Override
	public void hienThongTin() {
		if (this.monHocs == null)
			return;
		int n = this.monHocs.length;
		for (int i = 0; i < n; i++) {
			this.monHocs[i].hienThongTin();
		}

	}

	public MonHoc timMonHoc(int maMon) {
		if (this.monHocs == null)
			return null;
		int n = this.monHocs.length;
		for (int i = 0; i < n; i++) {
			if (this.monHocs[i].getMaMon() == maMon) {
				return this.monHocs[i];
			}
		}
		return null;
	}

	public int maMonHocLonNhat() {
		int max = MonHoc.getAuto();
		if (this.monHocs == null)
			return max;

		MonHoc[] danhSach = this.monHocs;
		int n = danhSach.length;
		for (int i = 0; i < n; i++) {
			if (danhSach[i].getMaMon() > max) {
				max = danhSach[i].getMaMon();
			}
		}
		return ++max;

	}

	// phan su ly them mon hoc moi
	public void taoMonHocMoi(Scanner sc) {
		// tim gia tri ma sinh vien lon nhat trong danh sach
		// va thay cho gia tri tu dong tang hien tai
		int maxID = this.maMonHocLonNhat();
		MonHoc.setAuto(maxID);
		this.nhapMonHoc(sc);
		// kiem tra luu file thanh cong hay khong nếu không in ra màn hình
		Boolean check = FileIODanhSachMonHoc.writeFileDanhSachMonHoc(this.monHocs);

		if (check) {
			this.hienThongTin();
		} else {
			System.out.println("Không lưu được thông tin vao file");

		}
	}

	// taọ một mảng danh sach mon
	private void nhapMonHoc(Scanner sc) {
		System.out.println("Số lượng môn học  muốn thêm: ");
		int soLuong = QuanLySinhVien.kiemTraDauVaoInt(sc);
		while (soLuong <= 0) {
			System.out.println("Số lượng lớn hơn 0 ");
			soLuong = QuanLySinhVien.kiemTraDauVaoInt(sc);
		}
		// nhap vao danh sach sach
		MonHoc monHoc;
		for (int i = 0; i < soLuong; i++) {
			monHoc = taoMonHoc(sc);
			this.themMonHoc(monHoc);
		}

	}

	private static MonHoc taoMonHoc(Scanner sc) {
		System.out.println("----------------------------\n");
		System.out.println("Nhập tên môn: ");
		String tenMon = sc.nextLine();
		;
		System.out.println("Nhập số đơn vị học trình : ");
		double soDonViHocTrinh = QuanLySinhVien.kiemTraDauVaoDouble(sc);
		while (soDonViHocTrinh < 1) {
			System.out.println("Nhập lại số đơn vị học trình ( lớn hơn 0): ");
			soDonViHocTrinh = QuanLySinhVien.kiemTraDauVaoDouble(sc);
		}
		System.out.println("Nhập loại môn: ");
		String loaiMon = sc.nextLine();
		MonHoc monHoc = new MonHoc(tenMon, soDonViHocTrinh, loaiMon);
		return monHoc;
	}

}
