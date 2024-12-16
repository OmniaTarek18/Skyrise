import { create } from "zustand";

const useSearchFlightDetails = create((set) => ({
    searchFlightDetails: {
    departureAirportId: "",
    arrivalAirportId: "",
    arrivalDate: "",
    departureDate: "",
    numberOfTickets: "",
    seatClass: "ECONOMY",
    tripType: "round-trip",
  },
  setSearchFlightDetails: (details) =>
    set((state) => ({
      searchFlightDetails: {
        ...state.searchFlightDetails,
        ...details, // Merge updated details with existing state
      },
    })),
}));

export default useSearchFlightDetails;
