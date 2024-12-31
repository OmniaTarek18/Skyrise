import React from 'react';
import { ThumbsUp, Meh, ThumbsDown } from 'lucide-react';
import './Slider.css';

const Slider = ({ value, onChange, label, onClear }) => {
  const options = [
    { value: 'Poor', icon: ThumbsDown, color: '#ef4444' },
    { value: 'Good', icon: Meh, color: '#f59e0b' },
    { value: 'Excellent', icon: ThumbsUp, color: '#22c55e' }
  ];

  const handleClick = (clickedValue) => {
    if (value === clickedValue && onClear) {
      onClear();
    } else {
      onChange(clickedValue);
    }
  };

  return (
    <div className="slider-section">
      <label className="slider-label">{label}</label>
      <div className="slider-options">
        {options.map((option) => {
          const Icon = option.icon;
          return (
            <button
              key={option.value}
              className={`slider-option ${value === option.value ? 'selected' : ''}`}
              onClick={() => handleClick(option.value)}
              style={{ '--highlight-color': option.color }}
            >
              <Icon className="slider-icon" size={24} />
              <span>{option.value}</span>
            </button>
          );
        })}
      </div>
    </div>
  );
};

export default Slider;