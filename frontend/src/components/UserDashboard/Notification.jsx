import React, { useState, useEffect } from "react";
import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Typography from "@mui/material/Typography";
import Tooltip from "@mui/material/Tooltip";
import IconButton from "@mui/material/IconButton";
import Box from "@mui/material/Box"; // Add this import
import Button from "@mui/material/Button"; // Add this import
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";
import { getNotification, updateNotification } from "../../api/flightsAfterSearch";

const Notification = ({ number, init, onClick }) => {
  const { id, role } = useUserAuthenticationStore();
  const [anchorElNo, setAnchorElNo] = useState(null);
  const [notifications, setNotifications] = useState(init);
  const [pageNum, setPageNum] = useState(0);
  const [hasMore, setHasMore] = useState(init.totalPages > pageNum);

  useEffect(() => {
    setNotifications(init);
  }, [init]);
 

  console.log(init);
  const handleOpenNoMenu = (event) => {
    setAnchorElNo(event.currentTarget);
  };

  const handleUpdateStatus = () => async (id, numOfNotification, notificationId) => {
    try {
      const data = await updateNotification(id, numOfNotification, notificationId);
    } catch (error) {
      console.error("Failed to update notifications:", error);
    }
  };
  const handleCloseNoMenu = () => {
    handleUpdateStatus(id, number,notifications[0].notificationId)
    setAnchorElNo(null);
  };
  const handleLoadMore = async () => {
    const newData = await onClick(id, pageNum + 1);
    setPageNum((prev) => prev + 1);
    setNotifications((prev) => [...prev, ...newData]);
  };

  return (
    <Box sx={{ flexGrow: 0, ml: 2 }}>
      <Tooltip title="Open settings">
        <IconButton size="large" color="inherit" onClick={handleOpenNoMenu}>
          <Badge badgeContent={number} color="error">
            <NotificationsIcon />
          </Badge>
        </IconButton>
      </Tooltip>
      <Menu
        anchorEl={anchorElNo}
        open={Boolean(anchorElNo)}
        onClose={handleCloseNoMenu}
      >
        {notifications &&
          notifications.map((notification, index) => (
            <MenuItem
              key={index}
              onClick={handleCloseNoMenu}
              sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "flex-start",
                gap: "4px",
                padding: "12px 16px",
                backgroundColor:
                  notification.status === "SEEN" ? "#f5f5f5" : "#ffffff",
                borderRadius: "12px",
                marginBottom: "10px",
                boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
                transition: "all 0.3s ease",
                "&:hover": {
                  backgroundColor: "#f0f4ff",
                  boxShadow: "0 6px 12px rgba(0, 0, 0, 0.15)",
                  transform: "translateY(-2px)",
                },
              }}
            >
              <Typography
                sx={{
                  fontSize: "16px",
                  fontWeight: "bold",
                  color: "#1a202c",
                  textOverflow: "ellipsis",
                  whiteSpace: "nowrap",
                  overflow: "hidden",
                  width: "100%",
                }}
              >
                {notification.title}
              </Typography>
              <Typography
                sx={{
                  fontSize: "14px",
                  fontWeight: "normal",
                  color: "#4a5568",
                  textOverflow: "ellipsis",
                  whiteSpace: "nowrap",
                  overflow: "hidden",
                  width: "100%",
                }}
              >
                {notification.message}
              </Typography>
            </MenuItem>
          ))}
        {
          <Button
            onClick={handleLoadMore}
            sx={{
              width: "100%",
              padding: "10px 0",
              fontWeight: "bold",
              backgroundColor: "#e0f7fa",
              "&:hover": {
                backgroundColor: "#b2ebf2",
              },
            }}
          >
            Show More
          </Button>
        }
      </Menu>
    </Box>
  );
};

export default Notification;
