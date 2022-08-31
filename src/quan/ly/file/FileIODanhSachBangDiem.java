package quan.ly.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import danh.sach.DanhSachBangDiem;
import danh.sach.DanhSachSinhVien;
import model.BangDiem;
import model.MonHoc;
import model.SinhVien;

public class FileIODanhSachBangDiem {

	public static boolean writeFileDanhSachBangDiem(BangDiem[] list) {

		if (list == null) {
			return false;
		}
		int n = list.length;
		int soMon;
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/file/DanhSachBangDiem"))) {

			for (int i = 0; i < n; i++) {

				bufferedWriter.write(list[i].getSinhVien().toString());
				bufferedWriter.newLine();
				soMon = list[i].soMon();
				bufferedWriter.write(soMon + "");
				bufferedWriter.newLine();
				// ghi so luong mon
				// song ghi lan luot mon va diem
				MonHoc[] danhSachMon = list[i].getDanhSachMon().getMonHocs();
				for (int j = 0; j < soMon; j++) {
					bufferedWriter.write(danhSachMon[j].toString() + "-" + list[i].getBangDiem()[j]);
					bufferedWriter.newLine();
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static DanhSachBangDiem readFileDanhSachBangDiem() {
		DanhSachBangDiem danhSach = new DanhSachBangDiem();
		BangDiem bangDiem;
		SinhVien sinhVien;
		MonHoc monHoc;

		try (BufferedReader br = new BufferedReader(new FileReader("src/file/DanhSachBangDiem"))) {
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				bangDiem = new BangDiem();
				// cat du lieu tai vi tri " " thành một mảng
				// cấu trúc lưu trong file: mã sv - tên - dia chi - soDienThoai - lop
				// lọc giá trị tương ứng rồi tạo đối tượng sinh viên
				String data[] = line.split("-");
				String id = data[0];
				String name = data[1];
				String diaChi = data[2];
				String soDienThoai = data[3];
				String lop = data[4];
//				// ep kieu du lieu ve int va double
				int maSV = Integer.parseInt(id);
				sinhVien = new SinhVien(maSV, name, diaChi, soDienThoai, lop);
				bangDiem.setSinhVien(sinhVien);

				// dọc số lượng để biết có bao nhiêu dòng môn cần đọc
				String soLuongMOn = line = br.readLine();
				int soLuong = Integer.parseInt(soLuongMOn);
				for (int i = 0; i < soLuong; i++) {
					line = br.readLine();
					// cat du lieu tai vi tri " "
					// cấu trúc lưu trong file: mã môn - tên môn - tin - loai mon - diem
					String dataMonHoc[] = line.split("-");
					String idMon = dataMonHoc[0];
					String tenMOn = dataMonHoc[1];
					String tinChi = dataMonHoc[2];
					String loaiMon = dataMonHoc[3];
					String diem = dataMonHoc[4];
//					// ep kieu du lieu ve int va double
					int maMon = Integer.parseInt(idMon);
					double tin = Double.parseDouble(tinChi);
					double diemMon = Double.parseDouble(diem);
					monHoc = new MonHoc(maMon, tenMOn, tin, loaiMon);
					bangDiem.themDiemVaoMon(monHoc, diemMon);
				}
				danhSach.setBangDiem(bangDiem);

			}

		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Dữ liệu trong file không khớp");
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return danhSach;
	}

}
