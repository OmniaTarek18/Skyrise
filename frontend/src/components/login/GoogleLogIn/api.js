export const GoogleLoginAPI = async (email) => {
  console.log(email);

  const url = `http://localhost:8080/email?email=${email}`;
  try {
    const response = await fetch(url);
    if (response.ok) {
      const json = await response.json();
      console.log(json);
      return json;
    }
  } catch (error) {
    console.error(error.message);
  }
  return null;
};
