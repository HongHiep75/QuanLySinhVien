package danh.sach;

import java.util.Scanner;

import chuc.nang.chung.HienThiTongTin;
import model.MonHoc;
import model.SinhVien;
import quan.ly.file.FileIODanhSachSinhVien;
import thuc.thi.chuong.trinh.QuanLySinhVien;

public class DanhSachSinhVien implements HienThiTongTin {
	private SinhVien[] sinhVien;

	public SinhVien[] getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien[] sinhVien) {
		if (sinhVien == null) return;

		if (this.sinhVien == null) {
			this.sinhVien = sinhVien;
		} else {
			int lenghtDanhSachSach = this.sinhVien.length;
			int leghtListSach = sinhVien.length;
			int lenghtTong = lenghtDanhSachSach + leghtListSach;
			SinhVien[] danhSachMoi = new SinhVien[lenghtTong];
			int n = 0;
			int m = 0;
			boolean ckeckDanhSach1 = true;
			boolean ckeckDanhSach2 = true;
			int trungGian = lenghtDanhSachSach;
			// them các phần tử của 2 mảng vòa danh sách mới
			while (ckeckDanhSach1 || ckeckDanhSach2) {
				// nếu danh sách đã them hết thì gán lại ckeckDanhSach1 để dừng vòng lặp
				if (n == lenghtDanhSachSach) {
					ckeckDanhSach1 = false;
				} else {
					danhSachMoi[n] = this.sinhVien[n];
					n++;
				}
				// nếu danh sách đã them hết thì gán lại ckeckDanhSach1 để dừng vòng lặp 
				// ckeckDanhSach1, ckeckDanhSach1 đều false thì dừng vòng lặp
				if (m == leghtListSach) {
					ckeckDanhSach2 = false;
				} else {
					danhSachMoi[trungGian] = sinhVien[m];
					m++;
					trungGian++;
				}

			}

			this.sinhVien = danhSachMoi;
		}
	}

	@Override
	public void hienThongTin() {
		if (this.sinhVien == null)
			return;
		int n = this.sinhVien.length;
		for (int i = 0; i < n; i++) {
			this.sinhVien[i].hienThongTin();
		}

	}

	public SinhVien timSinhVien(int maSinhVien) {
		if (this.sinhVien == null)
			return null;
		int n = this.sinhVien.length;
		for (int i = 0; i < n; i++) {
			if (this.sinhVien[i].getMaSinhVien() == maSinhVien) {
				return this.sinhVien[i];
			}
		}
		return null;
	}

	public void themSinhVien(SinhVien sinhVien) {
		if (sinhVien == null)
			return;
		if (this.sinhVien == null) {
			this.sinhVien = new SinhVien[1];
			this.sinhVien[0] = sinhVien;
			return;
		}
		int n = this.sinhVien.length;
		int nTong = n + 1;
		SinhVien[] bangMoi = new SinhVien[nTong];
		for (int i = 0; i < n; i++) {
			if(this.sinhVien[i] == null) {
				this.sinhVien[i] = sinhVien;
				return;
			}
			bangMoi[i] = this.sinhVien[i];
		}
		bangMoi[nTong - 1] = sinhVien;
		this.sinhVien = bangMoi;
	}
	// tim mã sinh viên lớn nhất để thay đổi giá trị của tự động tăng của sinh viên khi đọc file
	public int maSinhVienLonNhat() {
		int max = SinhVien.getAuto();
		if(this.sinhVien == null) return max;
		
		SinhVien[]  danhSachSinhVien = this.sinhVien;
		int n = danhSachSinhVien.length;
		for (int i = 0; i < n; i++) {
			if(sinhVien[i].getMaSinhVien() > max) {
				max = sinhVien[i].getMaSinhVien();
			}
		}
		return ++max;
		
	}
	
	//  them sinh vien va hien thi
	public void taoDanhSachSinhVien(Scanner sc) { 
		int maxID = this.maSinhVienLonNhat();
		SinhVien.setAuto(maxID);
		this.nhapSinhvien(sc);
		// nếu thêm thong tin vao file thành công thì hien thong tin bang diem
		Boolean check  = FileIODanhSachSinhVien.writeFileDanhSachSinhVien(this.sinhVien);
			if (check) {
				this.hienThongTin();
			} else {
				System.out.println("Không lưu được thông tin vui nhập lại");
			}
	}
	// tao mot danh sach sinh vien
	private  void nhapSinhvien(Scanner sc) {
		System.out.println("Số lượng sinh viên muốn thêm: ");
		int soLuong = QuanLySinhVien.kiemTraDauVaoInt(sc);
		while (soLuong <= 0) {
			System.out.println("Số lượng lớn hơn 0 ");
			soLuong = QuanLySinhVien.kiemTraDauVaoInt(sc);
		}

		// nhap vao danh sach sach
         SinhVien sinhVienMOi;
		for (int i = 0; i < soLuong; i++) {
			sinhVienMOi = taoSinhVien(sc);
			this.themSinhVien(sinhVienMOi);
		}

	}

	private SinhVien taoSinhVien(Scanner sc) {
		System.out.println("----------------------------\n");
		System.out.println("Nhập tên sinh viên: ");
		String tenSV = QuanLySinhVien.kiemTraDauVaoString(sc);
		System.out.println("Nhập địa chỉ: ");
		String tenDiaChi = sc.nextLine();
		System.out.println("Nhập tên lớp: ");
		String tenLop = sc.nextLine();
		System.out.println("Nhập số diện thoại: ");
		String chuoiSoDienThoai = QuanLySinhVien.kiemTraSoDienThoai(sc);
		SinhVien sinhVien = new SinhVien(tenSV, tenDiaChi, chuoiSoDienThoai, tenLop);
		return sinhVien;
	}

}
