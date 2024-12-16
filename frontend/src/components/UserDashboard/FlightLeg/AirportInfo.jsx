import React, { useEffect, useState } from "react";
import { Clock, MapPin } from "lucide-react";
import "./AirportInfo.css";
import axios from "axios";

const AirportInfo = ({
  time,
  airportName,
  airportCountry,
  airportCity,
  airportCode,
  type,
}) => {
  const [imageUrl, setImageUrl] = useState("");

  useEffect(() => {
    const fetchImage = async () => {
      try {
        const response = await axios.get(
          `https://api.unsplash.com/search/photos`,
          {
            params: {
              query: airportCity,
              page: 1,
              per_page: 1,
              order_by: "relevant",
              client_id: "f4IqvpzP2BnIcrol8zff5VHODow9MuC1kE2loEPZRcI",
            },
          }
        );
        if (response.data.results.length > 0) {
          setImageUrl(response.data.results[0].urls.regular);
        }
      } catch (error) {
        console.error("Error fetching image from Unsplash:", error);
      }
    };

    if (airportCity) {
      fetchImage();
    }
  }, [airportCity]);

  return (
    <div className={`airport-info ${type}`}>
      <div className="time-display">
        <Clock size={16} />
        <span>{time}</span>
      </div>

      <div className="airport-details">
        {imageUrl && (
          <div className="city-image">
            <div className="image-container">
              <img
                src={imageUrl}
                alt={`${airportCity}, ${airportCountry}`}
                loading="lazy"
              />
              <div className="image-overlay" />
            </div>
          </div>
        )}

        <div className="airport-code">{airportCode}</div>
        <div className="airport-location">
          <MapPin className="pin" size={16} />
          <span>{airportCity}</span>
        </div>
        <div className="airport-name">{airportName}</div>
        <div className="airport-country">{airportCountry}</div>
      </div>
    </div>
  );
};

export default AirportInfo;
