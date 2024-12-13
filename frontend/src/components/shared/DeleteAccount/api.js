export const deleteAccountAPI = async (id) => {
  console.log(id);
  const url = `http://localhost:8080/deleteAccount/${id}`;
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    console.log(json);
  } catch (error) {
    console.error(error.message);
  }
};
