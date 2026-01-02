package com.simats.eventdecorationitems;

import com.simats.eventdecorationitems.models.AddEquipmentRequest;
import com.simats.eventdecorationitems.models.AddEquipmentResponse;
import com.simats.eventdecorationitems.models.AdminLoginRequest;
import com.simats.eventdecorationitems.models.AdminLoginResponse;
import com.simats.eventdecorationitems.models.BookingApprovalRequest;
import com.simats.eventdecorationitems.models.BookingApprovalResponse;
import com.simats.eventdecorationitems.models.DashboardStatsResponse;
import com.simats.eventdecorationitems.models.EquipmentDetailsRequest;
import com.simats.eventdecorationitems.models.EquipmentDetailsResponse;
import com.simats.eventdecorationitems.models.EquipmentListResponse;
import com.simats.eventdecorationitems.models.GetNotificationsRequest;
import com.simats.eventdecorationitems.models.NotificationsResponse;
import com.simats.eventdecorationitems.models.PendingBookingsResponse;
import com.simats.eventdecorationitems.models.SendNotificationRequest;
import com.simats.eventdecorationitems.models.SendNotificationResponse;
import com.simats.eventdecorationitems.models.SignupRequest;
import com.simats.eventdecorationitems.models.SignupResponse;
import com.simats.eventdecorationitems.models.UnreadCountResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("admin_login.php")
    Call<AdminLoginResponse> adminLogin(@Body AdminLoginRequest request);

    @POST("signup.php")
    Call<SignupResponse> signupUser(@Body SignupRequest request);

    @POST("admin_equipment.php")
    Call<AddEquipmentResponse> addEquipment(@Query("action") String action, @Body AddEquipmentRequest request);

    @GET("admin_dashboard_stats.php")
    Call<DashboardStatsResponse> getDashboardStats();

    @GET("get_pending_bookings.php")
    Call<PendingBookingsResponse> getPendingBookings();

    @POST("booking_approval.php")
    Call<BookingApprovalResponse> approveBooking(@Body BookingApprovalRequest request);

    // Equipment management endpoints
    @GET("admin_equipment.php")
    Call<EquipmentListResponse> getEquipmentList(@Query("action") String action);

    @GET("admin_equipment.php")
    Call<AddEquipmentResponse> deleteEquipment(@Query("action") String action, @Query("id") int id);

    @POST("get_equipment_details.php")
    Call<EquipmentDetailsResponse> getEquipmentDetails(@Body EquipmentDetailsRequest request);

    // Notification endpoints
    @POST("send_notification.php")
    Call<SendNotificationResponse> sendNotification(@Body SendNotificationRequest request);

    @POST("notifications.php")
    Call<NotificationsResponse> getNotifications(@Body GetNotificationsRequest request);

    @POST("get_unread_count.php")
    Call<UnreadCountResponse> getUnreadCount(@Body GetNotificationsRequest request);
}





