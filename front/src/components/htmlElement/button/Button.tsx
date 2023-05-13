import styles from "@/src/components/htmlElement/button/button.module.css";

type ButtonProps = {
  type: "button" | "submit";
  disabled?: boolean;
  value: string;
};

export const Button: React.FC<ButtonProps> = ({ type, disabled, value }) => {
  return (
    <button type={type} disabled={disabled} className={styles.button}>
      {value}
    </button>
  );
};
