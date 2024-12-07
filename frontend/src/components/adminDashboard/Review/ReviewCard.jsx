import React from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
  Rating,
  Grid,
  Divider,
  Avatar,
} from "@mui/material";
import {
  FlightTakeoff as FlightTakeoffIcon,
  FlightLand as FlightLandIcon,
  FormatQuoteOutlined,
} from "@mui/icons-material";
import PerformanceRating from "./PerformanceRating";
import "./reviewcard.css";
import { purple } from "@mui/material/colors";

const ReviewCard = ({
  userName,
  source,
  destination,
  date,
  starRating,
  ratings,
  comments,
}) => {
  return (
    <Card className="review-card">
      <CardContent>
        {/* header containing avatar and user info and destination and source */}
        <Box className="header">
          <Avatar className="avatar" sx={{ backgroundColor: purple[500] }}>
            {userName[0].toUpperCase()}
          </Avatar>
          <Box className="header-info">
            <Typography variant="h6" className="user-name">
              {userName}
            </Typography>
            <Typography variant="subtitle2" className="flight-route">
              {source} <FlightTakeoffIcon fontSize="small" /> ➔ {destination}{" "}
              <FlightLandIcon fontSize="small" />
            </Typography>
          </Box>
          <Rating value={starRating} precision={0.5} readOnly size="small" />
        </Box>

        <Divider sx={{ mt: 2, mb: 2 }} />

        {/* performance rating row */}
        <Grid
          container
          spacing={2}
          justifyContent="space-between"
          alignItems="center"
          sx={{ mt: 2 }}
        >
          <PerformanceRating
            label="Punctuality"
            icon="schedule"
            rating={ratings.punctuality}
          />
          <PerformanceRating
            label="Comfort"
            icon="comfort"
            rating={ratings.comfort}
          />
          <PerformanceRating
            label="Service"
            icon="service"
            rating={ratings.service}
          />
          <PerformanceRating
            label="Food & Beverage"
            icon="food"
            rating={ratings.foodAndBeverage}
          />
          <PerformanceRating
            label="Cleanliness"
            icon="cleanliness"
            rating={ratings.cleanliness}
          />
        </Grid>

        <Divider sx={{ mt: 2, mb: 2 }} />

        {/* comment  */}
        {comments && (
          <>
            <Typography variant="body2" color="text.secondary">
              <FormatQuoteOutlined className="quote-icon" />
              {comments}
            </Typography>
          </>
        )}

        {/* date */}
        <Typography variant="caption" className="date">
          {date}
        </Typography>
      </CardContent>
    </Card>
  );
};

export default ReviewCard;
