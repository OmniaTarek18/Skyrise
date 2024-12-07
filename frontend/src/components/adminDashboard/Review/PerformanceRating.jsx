import React from "react";
import { Box, Typography, Chip } from "@mui/material";
import {
  Schedule as ScheduleIcon,
  AirlineSeatReclineExtra as ComfortIcon,
  ThumbUpAlt as ServiceIcon,
  RestaurantMenu as FoodIcon,
  CleaningServices as CleaningIcon,
} from "@mui/icons-material";

const ICONS = {
  schedule: <ScheduleIcon fontSize="small" />,
  comfort: <ComfortIcon fontSize="small" />,
  service: <ServiceIcon fontSize="small" />,
  food: <FoodIcon fontSize="small" />,
  cleanliness: <CleaningIcon fontSize="small" />,
};

// colors for performance labels shape
const COLORS = {
  excellent: "#a8e6a1",
  fair: "#ffd1a9",
  poor: "#f6a1c3",
  default: "#a2d5f2",
};

// darker shades for the label text to give contrast
const DARK_COLORS = {
  excellent: "#2d6a2f",
  fair: "#b96f38",
  poor: "#a84f72",
  default: "#004f6d",
};

const getColor = (rating) => {
  switch (rating) {
    case "EXCELLENT":
      return COLORS.excellent;
    case "FAIR":
      return COLORS.fair;
    case "POOR":
      return COLORS.poor;
    default:
      return COLORS.default;
  }
};

const getTextColor = (rating) => {
  switch (rating) {
    case "EXCELLENT":
      return DARK_COLORS.excellent;
    case "FAIR":
      return DARK_COLORS.fair;
    case "POOR":
      return DARK_COLORS.poor;
    default:
      return DARK_COLORS.default;
  }
};

const PerformanceRating = ({ label, icon, rating }) => (
  <Box sx={{ textAlign: "center", minWidth: 90 }}>
    {ICONS[icon]}
    <Typography
      variant="subtitle2"
      className="rating-label"
      sx={{ fontSize: "0.8rem", color: "#333" }}
    >
      {label}
    </Typography>
    <Chip
      label={rating}
      sx={{
        backgroundColor: getColor(rating),
        color: getTextColor(rating),
        marginTop: 0.5,
      }}
      size="small"
    />
  </Box>
);

export default PerformanceRating;
