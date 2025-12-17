document.addEventListener('DOMContentLoaded', function() {
    const cityInput = document.querySelector('.city-input');
    if (cityInput) cityInput.focus();
});

document.getElementById('weatherForm').addEventListener('submit', function() {
    document.getElementById('loading').style.display = 'block';

    const weatherCard = document.querySelector('.weather-card');
    const errorMsg = document.querySelector('.error-message');
    if (weatherCard) weatherCard.style.display = 'none';
    if (errorMsg) errorMsg.style.display = 'none';
});