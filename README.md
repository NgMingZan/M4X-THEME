# M4X THEME 4.0 – Liquid Glass

## Tính năng mới

- Giao diện kính lỏng
- Liên hệ Admin: https://t.me/bengtayy
- Telegram Channel: https://t.me/m4xthemestore
- Mục Ủng hộ Admin với mã VietQR
- Dữ liệu theme tải từ JSON
- Hỗ trợ cấu hình online
- Có thể đổi theme, banner, thông báo, giá điểm và link tải mà không cần cài lại APK

## Cập nhật online không cần tải lại app

Ứng dụng hỗ trợ 2 cấp:

### 1. Cập nhật dữ liệu online
Bạn có thể đặt file `themes.json` và `app-config.json` trên GitHub Pages hoặc hosting.

Trong `app-config.json`:

```json
{
  "onlineThemesUrl": "https://tenmien/themes.json",
  "onlineConfigUrl": "https://tenmien/app-config.json"
}
```

Sau đó APK sẽ tự lấy dữ liệu mới khi mở app.

Có thể cập nhật online:
- Theme mới
- Ảnh preview
- Link tải `.mtz`
- Giá điểm
- Banner/thông báo
- Liên kết Admin
- Nội dung bảo trì

### 2. Cập nhật toàn bộ giao diện/tính năng web
Muốn thay toàn bộ giao diện và JavaScript mà không cài APK mới, cần để WebView mở một website online thay vì `file:///android_asset/index.html`.

Khi đó chỉ cần sửa website trên hosting, người dùng mở app sẽ thấy bản mới.

Lưu ý: thay đổi mã Android gốc, quyền hệ thống hoặc DownloadManager vẫn cần phát hành APK mới.
