export const formatDate = (dateStr) => {
  const options = { weekday:'short',year:'numeric',month:'short',day:'numeric' };
  return new Date(dateStr).toLocaleDateString('en-US', options);
};

export const formatTime = (timeStr) => {
  return new Date(`2000-01-01T${timeStr}`).toLocaleTimeString('en-US', {
    hour: '2-digit',
    minute: '2-digit',
    hour12: true
  });
};